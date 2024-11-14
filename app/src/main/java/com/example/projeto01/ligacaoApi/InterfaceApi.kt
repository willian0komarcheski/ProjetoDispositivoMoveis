package com.example.projeto01.ligacaoApi

import com.example.projeto01.dados.AdviceResponse
import com.example.projeto01.dados.DogFactResponse
import com.example.projeto01.dados.FactResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("advice")
    suspend fun getAdvice(): Response<AdviceResponse>

        @GET("api/v2/facts/random")
        suspend fun getUselessFact(): Response <FactResponse>

    @GET("api/facts?number=1")
    suspend fun getDogFact(): Response<DogFactResponse>
}
