package com.example.projeto01.navegacao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projeto01.dados.Usuario
import com.example.projeto01.view.TelaCadastro
import com.example.projeto01.view.TelaListaUsuarios

@Composable
fun Navegacao() {
    val navController = rememberNavController()
    // Armazena a lista de usuários localmente na memória
    var usuarios by remember { mutableStateOf(listOf<Usuario>()) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "cachorro",
            Modifier.padding(innerPadding)
        ) {
            composable("cachorro") {
                // Tela de cadastro que adiciona o usuário à lista
                TelaCadastro(onCadastroConcluido = { novoUsuario ->
                    usuarios = usuarios + novoUsuario // Atualiza a lista de usuários
                })
            }
            composable("conselho") {
                // Tela de lista que exibe todos os usuários cadastrados
                TelaListaUsuarios(usuarios)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation {
        BottomNavigationItem(
            label = { Text("Cadastro") },
            icon = { Icon(Icons.Default.Info, contentDescription = null) },
            selected = navController.currentDestination?.route == "cachorro",
            onClick = { navController.navigate("cachorro") }
        )
        BottomNavigationItem(
            label = { Text("Lista de Usuários") },
            icon = { Icon(Icons.Default.Info, contentDescription = null) },
            selected = navController.currentDestination?.route == "conselho",
            onClick = { navController.navigate("conselho") }
        )
    }
}
