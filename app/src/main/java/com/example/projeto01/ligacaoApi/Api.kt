package com.example.projeto01.ligacaoApi

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://uselessfacts.jsph.pl/"
    private const val BASE_DOG_URL = "https://dog-api.kinduff.com/"
    private const val BASE_ADVICE_URL = "https://api.adviceslip.com/"

    // Instância do Gson com setLenient para aceitar JSON malformado
    private val gson = GsonBuilder()
        .setLenient()  // Permitir JSON lenient (malformado)
        .create()

    val uselessApi: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson)) // Passando o gson personalizado
            .build()
            .create(ApiService::class.java)
    }

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_ADVICE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson)) // Passando o gson personalizado
            .build()
            .create(ApiService::class.java)
    }

    val dogApi: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_DOG_URL)
            .addConverterFactory(GsonConverterFactory.create(gson)) // Passando o gson personalizado
            .build()
            .create(ApiService::class.java)
    }
}
