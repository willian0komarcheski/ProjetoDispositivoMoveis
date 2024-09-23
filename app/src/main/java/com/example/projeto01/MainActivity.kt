package com.example.projeto01

import AddTaskScreen
import TaskDetailsScreen
import TaskListScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import com.example.projeto01.Task

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}
@Composable
fun MyApp() {
    val navController = rememberNavController()
    var tasks by remember { mutableStateOf(mutableListOf<Task>()) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Lista de Tarefas") }) }
    ) { innerPadding ->
        NavHost(navController, startDestination = "task_list", Modifier.padding(innerPadding)) {
            composable("task_list") {
                TaskListScreen(
                    navController,
                    tasks,
                    onDeleteAllTasks = { tasks.clear() } // Limpa a lista de tarefas
                )
            }
            composable("add_task") { AddTaskScreen(navController) { task ->
                tasks.add(task)
            }}
            composable("task_details/{taskId}") { backStackEntry ->
                val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
                val task = tasks.find { it.id == taskId }
                if (task != null) {
                    TaskDetailsScreen(navController, task)
                }
            }
        }
    }
}

