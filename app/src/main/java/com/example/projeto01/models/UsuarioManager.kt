package com.example.projeto01.models

import android.util.Log
import com.example.projeto01.dados.Usuario
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class UsuarioManager {
    private val database: DatabaseReference = Firebase.database.reference.child("usuarios")

    fun adicionarUsuario(usuario: Usuario) {
        val key = database.push().key ?: return
        usuario.id = key // Define o ID gerado automaticamente pelo Firebase
        database.child(key).setValue(usuario) // Salva o objeto completo no Firebase
            .addOnSuccessListener {
                Log.d("UsuarioManager", "Usuário salvo com sucesso!")
            }
            .addOnFailureListener { e ->
                Log.e("UsuarioManager", "Erro ao salvar usuário: ${e.message}")
            }
    }

    fun getOne(key: String, callback: (Usuario?) -> Unit) {
        database.child(key).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val usuario = snapshot.getValue(Usuario::class.java)
                callback(usuario) // Retorna o usuário ou null se não encontrado
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("UsuarioManager", "Erro ao recuperar usuário: ${error.message}")
                callback(null) // Caso haja erro, retorna null
            }
        })
    }


    fun editarUsuario(key: String, usuario: Usuario) {
        database.child(key).setValue(usuario)
    }

    fun removerUsuario(key: String) {
        database.child(key).removeValue()
    }

    fun obterUsuarios(callback: (Map<String, Usuario>) -> Unit) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Transforme os dados do snapshot em um Map<String, Usuario>, ignorando valores nulos
                val usuariosMap = snapshot.children.mapNotNull { data ->
                    val key = data.key
                    val usuario = data.getValue(Usuario::class.java)
                    if (key != null && usuario != null) {
                        key to usuario // Retorna um par apenas se ambos não forem nulos
                    } else {
                        null // Ignora itens nulos
                    }
                }.toMap() // Converte a lista de pares em um mapa

                callback(usuariosMap)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("UsuarioManager", "Erro ao recuperar usuários: ${error.message}")
            }
        })
    }

}
