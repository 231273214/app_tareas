package com.example.app_tareas.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.app_tareas.ui.components.TaskItem
import com.example.app_tareas.ui.viewmodels.TaskViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.app_tareas.data.database.AppDatabase
import com.example.app_tareas.ui.viewmodels.TaskViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    viewModel: TaskViewModel = viewModel(
        factory = TaskViewModelFactory(
            Room.databaseBuilder(
                LocalContext.current.applicationContext,
                AppDatabase::class.java,
                "tasks-db"
            ).build().taskDao()
        )
    ),
    navController: NavController
) {
    val tasks by viewModel.tasks.collectAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("addTask") }
            ) {
                Icon(Icons.Default.Add, "Añadir tarea")
            }
        },
        topBar = {
            TopAppBar(
                title = { Text("Mis Tareas") },
                actions = {
                    IconButton(onClick = { viewModel.deleteCompletedTasks() }) {
                        Icon(Icons.Default.Delete, "Limpiar completadas")
                    }
                }
            )
        }
    ) { padding ->
        if (tasks.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No hay tareas disponibles")
            }
        } else {
            LazyColumn(modifier = Modifier.padding(padding)) {
                items(tasks) { task ->
                    TaskItem(
                        task = task,
                        onTaskClick = { navController.navigate("editTask/${task.id}") },
                        onCompleteTask = { viewModel.completeTask(task) },
                        onDeleteTask = { viewModel.deleteTask(task) }
                    )
                    Divider()
                }
            }
        }
    }
}