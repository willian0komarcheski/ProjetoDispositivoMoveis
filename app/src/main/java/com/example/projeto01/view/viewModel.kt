package com.example.projeto01.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.example.projeto01.ligacaoApi.RetrofitInstance

class MainViewModel : ViewModel() {
    private val _advice = mutableStateOf("")
    private val _uselessFact = mutableStateOf("")
    private val _dogFact = mutableStateOf("")

    val advice: State<String> get() = _advice
    val uselessFact: State<String> get() = _uselessFact
    val dogFact: State<String> get() = _dogFact

    fun fetchAdvice() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getAdvice()
                if (response.isSuccessful) {
                    _advice.value = response.body()?.slip?.advice ?: "No advice available."
                } else {
                    _advice.value = "Error: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                _advice.value = "Error: ${e.message}"
            }
        }
    }

    fun fetchUselessFact() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.uselessApi.getUselessFact()
                if (response.isSuccessful) {
                    _uselessFact.value = response.body()?.text ?: "No fact available."
                } else {
                    _uselessFact.value = "Error: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                _uselessFact.value = "Error: ${e.message}"
            }
        }
    }

    fun fetchDogFact() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.dogApi.getDogFact()
                if (response.isSuccessful) {
                    _dogFact.value = response.body()?.facts?.firstOrNull() ?: "No dog fact found."
                } else {
                    _dogFact.value = "Error: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                _dogFact.value = "Error: ${e.message}"
            }
        }
    }
}
