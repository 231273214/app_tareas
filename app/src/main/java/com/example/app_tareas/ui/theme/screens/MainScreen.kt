package com.example.app.lareas.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.app.lareas.ui.components.TareaItem
import com.example.app.lareas.viewmodel.TareaViewModel
import com.example.app_tareas.database.entities.Tarea

@Composable
fun MainScreen(viewModel: TareaViewModel = hiltViewModel()) {
    val tareas by viewModel.tareas.collectAsState(initial = emptyList())

    LazyColumn {
        items(
            items = tareas,  // Lista de tareas
            key = { tarea -> tarea.id }  // Clave única para cada elemento
        ) { tarea ->
            TareaItem(
                tarea = tarea,
                onDelete = { viewModel.eliminarTarea(it) }
            )
        }
    }
}

