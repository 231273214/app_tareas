package com.example.app_tareas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.app_tareas.data.database.AppDatabase
import com.example.app_tareas.data.entities.Task
import com.example.app_tareas.ui.AppNavigation
import com.example.app_tareas.ui.theme.App_TareasTheme
import com.example.app_tareas.ui.viewmodels.TaskViewModel
import com.example.app_tareas.ui.viewmodels.TaskViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date
import kotlinx.coroutines.flow.firstOrNull

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "tasks-db"
        ).fallbackToDestructiveMigration()
            .build()

        // Cambiar GlobalScope por lifecycleScope
        lifecycleScope.launch(Dispatchers.IO) {
            if (db.taskDao().getAllTasks().firstOrNull().isNullOrEmpty()) {
                db.taskDao().insertTask(
                    Task(
                        title = "Primera tarea",
                        description = "Completar el proyecto",
                        priority = Task.Priority.URGENTE,
                        group = "Trabajo",
                        creationDate = Date(),
                        dueDate = Date(System.currentTimeMillis() + 86400000), // Mañana
                        completionDate = null
                    )
                )
            }
        }



        setContent {
            App_TareasTheme {
                // Añadir Surface con color de fondo
                Surface(

                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: TaskViewModel = viewModel(
                        factory = TaskViewModelFactory(db.taskDao())
                    )
                    AppNavigation(viewModel = viewModel)
                }
            }
        }
    }
}