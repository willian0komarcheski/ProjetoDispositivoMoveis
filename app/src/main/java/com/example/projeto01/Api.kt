package com.example.projeto01

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://uselessfacts.jsph.pl/"
    private const val BASE_DOG_URL = "https://dog-api.kinduff.com/"
    private const val BASE_ADVICE_URL = "https://api.adviceslip.com/"


    val uselessApi: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_ADVICE_URL) // Usaremos isso como base para a primeira API
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    val dogApi: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_DOG_URL) // Usaremos isso como base para a API de fatos de cachorro
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
