package com.example.projeto01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp { // Chama a função MyApp que envolve o Scaffold
                MainScreen()
            }
        }
    }
}

// Função para aplicar o tema Material
@Composable
fun MyApp(content: @Composable () -> Unit) {
    MaterialTheme {
        Surface {
            content() // O conteúdo aqui será envolvido pelo tema Material
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "advice",
            Modifier.padding(innerPadding) // Adiciona padding para evitar sobreposição com a barra
        ) {
            composable("advice") { AdviceScreen() }
            composable("useless_fact") { UselessFactScreen() }
            composable("dog_fact") { DogFactScreen() }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation() {
        BottomNavigationItem(
            label = { Text("Advice") },
            icon = { Icon(Icons.Default.Info, contentDescription = null) },
            selected = navController.currentDestination?.route == "advice",
            onClick = { navController.navigate("advice") }
        )
        BottomNavigationItem(
            label = { Text("Useless Fact") },
            icon = { Icon(Icons.Default.Info, contentDescription = null) },
            selected = navController.currentDestination?.route == "useless_fact",
            onClick = { navController.navigate("useless_fact") }
        )
        BottomNavigationItem(
            label = { Text("Dog Fact") },
            icon = { Icon(Icons.Default.Info, contentDescription = null) },
            selected = navController.currentDestination?.route == "dog_fact",
            onClick = { navController.navigate("dog_fact") }
        )
    }
}

@Composable
fun AdviceScreen() {
    val viewModel: MainViewModel = viewModel()
    LaunchedEffect(Unit) { viewModel.fetchAdvice() }
    Text(text = viewModel.advice.value)
}

@Composable
fun UselessFactScreen() {
    val viewModel: MainViewModel = viewModel()
    LaunchedEffect(Unit) { viewModel.fetchUselessFact() }
    Text(text = viewModel.uselessFact.value)
}

@Composable
fun DogFactScreen() {
    val viewModel: MainViewModel = viewModel()
    LaunchedEffect(Unit) { viewModel.fetchDogFact() }
    Text(text = viewModel.dogFact.value)
}
