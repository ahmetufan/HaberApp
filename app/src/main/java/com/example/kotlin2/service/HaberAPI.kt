package com.example.kotlin2.service

import com.example.kotlin2.Haber
import com.example.kotlin2.HaberResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface HaberAPI {

    @GET("news/getNews")
    fun getAPI(
        @Query("country") country: String,
        @Query("tag") tag: String,
        @Header("Authorization") apikey: String
    ): Single<HaberResult>



}