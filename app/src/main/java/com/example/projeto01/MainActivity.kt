package com.example.projeto01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.example.projeto01.models.UsuarioManager
import com.example.projeto01.navegacao.Navegacao
import com.google.firebase.database.FirebaseDatabase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)


        setContent {
            MyApp()
        }
    }


    @Composable
    fun MyApp() {
        // Inicializar o Firebase Database dentro da função composable
        val usuarioManager = UsuarioManager()

        MaterialTheme {
            Surface {
                Navegacao(usuarioManager)
            }
        }
    }
}