package com.example.app_tareas.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.app_tareas.ui.screens.AddTaskScreen
import com.example.app_tareas.ui.screens.EditTaskScreen
import com.example.app_tareas.ui.screens.TaskListScreen
import com.example.app_tareas.ui.viewmodels.TaskViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.material3.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp

@Composable
fun AppNavigation(viewModel: TaskViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "taskList"
    ) {
        composable("taskList") {
            TaskListScreen(viewModel, navController)
        }

        composable("addTask") {
            AddTaskScreen(viewModel, navController)
        }

        // Nueva ruta para edición con parámetro seguro
        composable(
            route = "editTask/{taskId}",
            arguments = listOf(
                navArgument("taskId") {
                    type = NavType.IntType
                    defaultValue = -1  // Valor por defecto inválido
                }
            )
        ) { backStackEntry ->
            // Manejo seguro del argumento
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: -1

            if (taskId == -1) {
                // Manejo de error
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Error: ID de tarea inválido")
                }
            } else {
                EditTaskScreen(
                    taskId = taskId,
                    viewModel = viewModel,
                    navController = navController
                )
            }
        }
    }
}