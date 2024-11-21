package com.example.projeto01.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projeto01.dados.Usuario
import com.example.projeto01.models.UsuarioManager

@Composable
fun TelaCadastro(onCadastroConcluido: () -> Unit) {
    val usuarioManager = UsuarioManager()
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val novoUsuario = Usuario(nome = nome, email = email)
            usuarioManager.adicionarUsuario(novoUsuario)
            nome = ""
            email = ""
            onCadastroConcluido() // Navega para a tela de lista
        }) {
            Text("Cadastrar")
        }
    }
}

@Composable
fun TelaListaUsuarios(usuarioManager: UsuarioManager, navController: NavController) {
    var usuarios by remember { mutableStateOf(mapOf<String, Usuario>()) }

    usuarioManager.obterUsuarios { novosUsuarios ->
        usuarios = novosUsuarios
    }

    LazyColumn {
        items(usuarios.entries.toList()) { (id, usuario) ->
            UsuarioItem(
                usuario = usuario,
                onEditar = {
                    // Passando o usuário para a tela de edição
                    navController.navigate("editar/${usuario.id}")
                },
                onDeletar = { usuarioManager.removerUsuario(id) }
            )
        }
    }
}


@Composable
fun TelaEdicaoUsuario(
    usuarioId: String,
    usuarioManager: UsuarioManager,
    navController: NavController
) {
    var usuario by remember { mutableStateOf<Usuario?>(null) }

    // Lançando a busca do usuário dentro de LaunchedEffect
    LaunchedEffect(usuarioId) {
        usuarioManager.getOne(usuarioId) { usuarioRecuperado ->
            usuario = usuarioRecuperado
        }
    }

    // A tela de edição só será exibida quando o usuário for recuperado
    if (usuario != null) {
        var nome by remember { mutableStateOf(usuario!!.nome) }
        var email by remember { mutableStateOf(usuario!!.email) }

        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        val usuarioEditado = Usuario(nome = nome, email = email)
                        usuarioManager.editarUsuario(usuario!!.id, usuarioEditado)
                        navController.navigate("lista")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Salvar", color = MaterialTheme.colorScheme.onPrimary)
                }

                Button(
                    onClick = { navController.navigate("lista") },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text("Cancelar", color = MaterialTheme.colorScheme.onSecondary)
                }
            }
        }
    } else {
        // Caso o usuário ainda não tenha sido recuperado
        CircularProgressIndicator()
    }
}



@Composable
fun UsuarioItem(
    usuario: Usuario,
    onEditar: (Usuario) -> Unit,
    onDeletar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Nome: ${usuario.nome}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Email: ${usuario.email}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Button(
                    onClick = { onEditar(usuario) },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Editar", color = MaterialTheme.colorScheme.onPrimary)
                }

                Button(
                    onClick = onDeletar,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Deletar", color = MaterialTheme.colorScheme.onError)
                }
            }
        }
    }
}
