package com.example.projeto01.navegacao

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projeto01.models.UsuarioManager
import com.example.projeto01.view.TelaCadastro
import com.example.projeto01.view.TelaEdicaoUsuario
import com.example.projeto01.view.TelaListaUsuarios

@Composable
fun Navegacao(usuarioManager: UsuarioManager) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "cadastro",
            Modifier.padding(innerPadding)
        ) {
            composable("cadastro") {
                TelaCadastro(onCadastroConcluido = { navController.navigate("lista") })
            }
            composable("lista") {
                TelaListaUsuarios(usuarioManager = usuarioManager, navController = navController)
            }
            composable("editar/{usuarioId}") { backStackEntry ->
                val usuarioId = backStackEntry.arguments?.getString("usuarioId")
                if (usuarioId != null) {
                    TelaEdicaoUsuario(
                        usuarioId = usuarioId,
                        usuarioManager = usuarioManager,
                        navController = navController
                    )
                }
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
            selected = navController.currentDestination?.route == "cadastro",
            onClick = { navController.navigate("cadastro") }
        )
        BottomNavigationItem(
            label = { Text("Lista de Usuários") },
            icon = { Icon(Icons.Default.Info, contentDescription = null) },
            selected = navController.currentDestination?.route == "lista",
            onClick = { navController.navigate("lista") }
        )
    }
}
