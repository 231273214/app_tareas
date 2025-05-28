package com.example.app_tareas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.room.Room
import com.example.app_tareas.data.database.AppDatabase
import com.example.app_tareas.ui.AppNavigation
import com.example.app_tareas.ui.viewmodels.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "tasks-db"
        ).build()

        val viewModel = TaskViewModel(db.taskDao())

        setContent {
            App_tareasTheme {
                AppNavigation(viewModel = viewModel)
            }
        }
    }
}

private fun MainActivity.App_tareasTheme(function: @Composable () -> Unit) {}
