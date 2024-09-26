package com.example.projeto01

data class AdviceResponse(
    val slip: Slip
)

data class Slip(
    val advice: String
)

data class FactResponse(
    val text: String
)

data class DogFactResponse(
    val facts: List<String>
)



