package com.example.app_tareas.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_tareas.ui.screens.TaskListScreen
import com.example.app_tareas.ui.viewmodels.TaskViewModel

@Composable
fun AppNavigation(viewModel: TaskViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "taskList"
    ) {
        composable("taskList") {
            TaskListScreen(viewModel = viewModel, navController = navController)
        }
        // Agrega más pantallas aquí
    }
}