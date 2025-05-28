package com.example.app_tareas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app_tareas.ui.components.TaskItem
import com.example.app_tareas.ui.viewmodels.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    viewModel: TaskViewModel,
    navController: NavController
) {
    val tasks by viewModel.pendingTasks.collectAsState(initial = emptyList())
    var searchQuery by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Tareas") },
                actions = {
                    IconButton(onClick = { active = !active }) {
                        Icon(Icons.Default.Search, contentDescription = "Buscar")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Navegar a pantalla de creación */ }) {
                Icon(Icons.Default.Add, contentDescription = "Añadir tarea")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            SearchBar(
                query = searchQuery,
                onQueryChange = { newQuery -> searchQuery = newQuery },
                onSearch = { /* Puedes implementar lógica adicional al realizar la búsqueda */ },
                active = active,
                onActiveChange = { active = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Buscar tareas...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
            ) {
                // Contenido de resultados de búsqueda si lo necesitas
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(tasks) { task ->
                    TaskItem(
                        task = task,
                        onTaskClick = { /* Navegar a detalles */ },
                        onCompleteTask = { viewModel.completeTask(task) }
                    )
                }
            }
        }
    }
}