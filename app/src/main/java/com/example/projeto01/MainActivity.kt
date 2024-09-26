package com.example.projeto01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel: MainViewModel by viewModels()


        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                Navegacao(viewModel)
            }
        }
    }


    @Composable
    fun MyApp(content: @Composable () -> Unit) {
        MaterialTheme {
            Surface {
                content()
            }
        }
    }

    @Composable
    fun Navegacao(viewModel: MainViewModel) {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController)
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "conselho",
                Modifier.padding(innerPadding)
            ) {
                composable("conselho") { TelaConselho(viewModel) }
                composable("inutil") { TelaFatos(viewModel) }
                composable("cachorro") { TelaCachorro(viewModel) }
            }
        }
    }

    @Composable
    fun BottomNavigationBar(navController: NavController) {
        BottomNavigation() {
            BottomNavigationItem(
                label = { Text("conselho") },
                icon = { Icon(Icons.Default.Info, contentDescription = null) },
                selected = navController.currentDestination?.route == "conselho",
                onClick = { navController.navigate("conselho") }
            )
            BottomNavigationItem(
                label = { Text("fatos inuteis") },
                icon = { Icon(Icons.Default.Info, contentDescription = null) },
                selected = navController.currentDestination?.route == "inutil",
                onClick = { navController.navigate("inutil") }
            )
            BottomNavigationItem(
                label = { Text("curiosidade sobre cachorro") },
                icon = { Icon(Icons.Default.Info, contentDescription = null) },
                selected = navController.currentDestination?.route == "cachorro",
                onClick = { navController.navigate("cachorro") }
            )
        }
    }

    @Composable
    fun TelaConselho(viewModel: MainViewModel) {
        var topico by remember { mutableStateOf("Aperte o botão para receber um conselho") }
        val conselho by viewModel.advice
        Text(topico)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { viewModel.fetchAdvice() }) {
                    Text("Conselho")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(conselho)
            }
        }
    }

    @Composable
    fun TelaFatos(viewModel: MainViewModel) {
        var topico by remember { mutableStateOf("Aperte o botão para receber um fato inutil") }
        val fatos by viewModel.uselessFact
        Text(topico)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { viewModel.fetchUselessFact() }) {
                    Text("fatos")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(fatos)
            }

        }
    }


        @Composable
        fun TelaCachorro(viewModel: MainViewModel) {
            var topico by remember { mutableStateOf("Aperte o botão para receber um fato sobre cachorro") }
            val cachorro by viewModel.dogFact
            Text(topico)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { viewModel.fetchDogFact() }) {
                        Text("cachorro")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(cachorro)
                }

            }
        }
    }