package com.example.projeto01.dados

data class AdviceResponse(
    val slip: Slip
)

data class Slip(
    val advice: String
)

data class FactResponse(
    val id: String,
    val text: String
)

data class DogFactResponse(
    val facts: List<String>
)

data class Usuario(
    val nome: String, val email: String
)

