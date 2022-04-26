package com.example.kotlin2.service

import com.example.kotlin2.HaberResult
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class HaberAPIService {
    val BASE_URL="https://api.collectapi.com/"
    val api_key="apikey 1BZycnisKZCcDv9hF10pdl:65vV4sqccL7I29VqW1p64O"
    val tag="general"
    val country="tr"


    val retrofit=Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(HaberAPI::class.java)

    fun getData(): Single<HaberResult> {
        return retrofit.getAPI(country,tag,api_key)
    }

}