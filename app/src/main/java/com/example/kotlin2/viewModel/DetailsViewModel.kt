package com.example.kotlin2.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin2.Haber
import com.example.kotlin2.HaberResult
import com.example.kotlin2.R
import com.example.kotlin2.service.HaberAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DetailsViewModel :ViewModel(){


    val detailsHaber=MutableLiveData<Haber>()

    fun getDetail(){
        val deta=Haber("1","1","1","1")

        detailsHaber.value=deta
    }




}