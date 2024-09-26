package com.example.projeto01

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("advice")
    suspend fun getAdvice(): Response<AdviceResponse>

    @GET("random")
    suspend fun getUselessFact(): Response<FactResponse>

    @GET("api/facts?number=1")
    suspend fun getDogFact(): Response<DogFactResponse>
}
