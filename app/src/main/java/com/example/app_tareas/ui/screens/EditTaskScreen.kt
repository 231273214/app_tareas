package com.example.app_tareas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app_tareas.data.entities.Task
import com.example.app_tareas.ui.viewmodels.TaskViewModel
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import com.example.app_tareas.ui.screens.EditTaskScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskScreen(
    taskId: Int,
    viewModel: TaskViewModel,
    navController: NavController
) {
    // Estado para manejar errores
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Obtener la tarea con manejo de errores
    val task by viewModel.getTaskById(taskId).collectAsState(initial = null)

    // Verificar si hubo error al cargar
    LaunchedEffect(task) {
        if (task == null) {
            errorMessage = "No se encontró la tarea"
        }
    }

    // Mostrar diálogo de error si es necesario
    if (errorMessage != null) {
        AlertDialog(
            onDismissRequest = {
                errorMessage = null
                navController.popBackStack()
            },
            title = { Text("Error") },
            text = { Text(errorMessage!!) },
            confirmButton = {
                TextButton(onClick = {
                    errorMessage = null
                    navController.popBackStack()
                }) {
                    Text("OK")
                }
            }
        )
        return
    }

    // Si la tarea aún no se ha cargado
    if (task == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }
    // Estado editable de la tarea
    var editedTask by remember { mutableStateOf(task!!) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Tarea") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, "Volver")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            try {
                                viewModel.updateTask(editedTask)
                                navController.popBackStack()
                            } catch (e: Exception) {
                                errorMessage = "Error al guardar: ${e.message}"
                            }
                        }
                    ) {
                        Icon(Icons.Default.Check, "Guardar")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Campo de título
            OutlinedTextField(
                value = editedTask.title,
                onValueChange = { editedTask = editedTask.copy(title = it) },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de descripción
            OutlinedTextField(
                value = editedTask.description ?: "",
                onValueChange = { editedTask = editedTask.copy(description = it) },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Selector de prioridad
            var priorityExpanded by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(
                expanded = priorityExpanded,
                onExpandedChange = { priorityExpanded = !priorityExpanded }
            ) {
                OutlinedTextField(
                    value = editedTask.priority.toString(),
                    onValueChange = {},
                    label = { Text("Prioridad") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = priorityExpanded)
                    }
                )

                ExposedDropdownMenu(
                    expanded = priorityExpanded,
                    onDismissRequest = { priorityExpanded = false }
                ) {
                    Task.Priority.values().forEach { priority ->
                        DropdownMenuItem(
                            text = { Text(priority.toString()) },
                            onClick = {
                                editedTask = editedTask.copy(priority = priority)
                                priorityExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Mostrar fecha de vencimiento
            Text(
                text = "Vence: ${SimpleDateFormat("dd/MM/yyyy").format(editedTask.dueDate)}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}