import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projeto01.Task
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.projeto01.R

@Composable
fun TaskListScreen(navController: NavController, tasks: List<Task>) {
    Column(modifier = Modifier.padding(16.dp)) {
        Button(
            onClick = { navController.navigate("add_task") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Adicionar Nova Tarefa")
        }

        Spacer(modifier = Modifier.height(16.dp))

        for (task in tasks) {
            TaskItem(task) {
                navController.navigate("task_details/${task.id}")
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}



@Composable
fun AddTaskScreen(navController: NavController, onAddTask: (Task) -> Unit) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descrição") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val task = Task(id = (0..1000).random(), title = title, description = description)
                onAddTask(task)
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar Tarefa")
        }
    }
}


@Composable
fun TaskDetailsScreen(navController: NavController, task: Task) {
    var title by remember { mutableStateOf(task.title) }
    var description by remember { mutableStateOf(task.description) }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descrição") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                task.title = title
                task.description = description
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Atualizar Tarefa")
        }
    }
}


@Composable
fun TaskListScreen(navController: NavController, tasks: List<Task>, onDeleteAllTasks: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Lista de Tarefas") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onDeleteAllTasks() },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete), // O ícone da lixeira
                    contentDescription = "Delete All Tasks"
                )
            }
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(16.dp)) {
                Button(
                    onClick = { navController.navigate("add_task") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Adicionar Nova Tarefa")
                }

                Spacer(modifier = Modifier.height(16.dp))

                for (task in tasks) {
                    TaskItem(task) {
                        navController.navigate("task_details/${task.id}")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    )
}

@Composable
fun TaskItem(task: Task, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = task.title, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = task.description, style = MaterialTheme.typography.body2)
        }
    }
}
