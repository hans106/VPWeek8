package com.example.vpweek8.Soal2.data.container

import com.example.vpweek8.Soal2.data.service.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val apiService: ApiService
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL = " https://www.theaudiodb.com/api/v1/json/123/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    override val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}