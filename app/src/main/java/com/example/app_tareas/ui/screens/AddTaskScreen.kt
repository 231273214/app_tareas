package com.example.app_tareas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app_tareas.data.entities.Task
import com.example.app_tareas.ui.components.DatePickerButton
import com.example.app_tareas.ui.components.PrioritySelector
import com.example.app_tareas.ui.viewmodels.TaskViewModel
import androidx.compose.material3.ExtendedFloatingActionButton
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    viewModel: TaskViewModel,
    navController: NavController
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedPriority by remember { mutableStateOf(Task.Priority.IMPORTANT) }
    var group by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf(Date()) }

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nueva Tarea") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.addTask(
                        title = title,
                        description = description,
                        priority = selectedPriority,
                        group = group,
                        dueDate = dueDate
                    )
                    navController.popBackStack()
                },
                containerColor = if (title.isNotBlank()) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.surfaceVariant
            ) {
                Icon(
                    imageVector = Icons.Filled.Check, // Asegúrate de importar Icons.Filled
                    contentDescription = "Guardar"
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Título*") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )

            Spacer(modifier = Modifier.height(16.dp))

            PrioritySelector(
                selectedPriority = selectedPriority,
                onPrioritySelected = { selectedPriority = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = group,
                onValueChange = { group = it },
                label = { Text("Grupo/Categoría") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            DatePickerButton(
                selectedDate = dueDate,
                onDateSelected = { dueDate = it }
            )
        }
    }
}