package com.example.kotlin2.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin2.Haber
import com.example.kotlin2.HaberResult
import com.example.kotlin2.service.HaberAPI
import com.example.kotlin2.service.HaberAPIService
import com.example.kotlin2.service.HaberDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : BaseViewModel(application) {
    private val haberAPIService = HaberAPIService()
    private val compositeDisposable = CompositeDisposable()

    val habers = MutableLiveData<List<Haber>>()
    val haberError = MutableLiveData<Boolean>()
    val haberLoading = MutableLiveData<Boolean>()

    fun refreshing() {

        getDataFromAPI()
    }
    init {
        getDataFromSQLite()
    }

    private fun getDataFromAPI() {

        haberLoading.value = true

        compositeDisposable.add(
            haberAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<HaberResult>() {
                    override fun onSuccess(t: HaberResult) {
                        storeInSQLite(t.result)
                    }

                    override fun onError(e: Throwable) {
                        haberError.value = true
                        haberLoading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }
    private fun getDataFromSQLite(){
        launch {
            val habers=HaberDatabase(getApplication()).haberDao().getAllHaber()
            showHabers(habers)
        }
    }

    private fun showHabers(haberResult: List<Haber>) {
        habers.value = haberResult
        haberError.value = false
        haberLoading.value = false
    }

    private fun storeInSQLite(haberResultSQLite: List<Haber>) {
        launch {
            val dao=HaberDatabase(getApplication()).haberDao()
            dao.deleteHabers()
            dao.insertAll(haberResultSQLite)
            /*var i=0
            while (i < long.size) {
                haberResultSQLite[i]
                i++
            }*/
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}