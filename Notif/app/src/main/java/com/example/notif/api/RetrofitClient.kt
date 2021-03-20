package com.example.notif.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val client=OkHttpClient.Builder().build()

    private val retrofit=Retrofit.Builder()
        .baseUrl("http://192.168.43.4:8000/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
    fun<T> buildService(service: Class<T>):T{
        return retrofit.create(service)
    }
}
