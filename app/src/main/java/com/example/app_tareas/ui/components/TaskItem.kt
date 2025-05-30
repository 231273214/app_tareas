package com.example.app_tareas.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.app_tareas.data.entities.Task
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.*

import java.util.*

@Composable
fun TaskItem(
    task: Task,
    onTaskClick: () -> Unit,
    onCompleteTask: () -> Unit,
    onDeleteTask: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onTaskClick() }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = { onCompleteTask() }
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(task.title, style = MaterialTheme.typography.titleMedium)
                task.description?.let {
                    Text(it, style = MaterialTheme.typography.bodyMedium)
                }
            }

            IconButton(onClick = onDeleteTask) {
                Icon(Icons.Default.Delete, "Eliminar")
            }
        }
    }
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Grupo
            Text(
                text = task.group ?: "Sin grupo",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Título y Checkbox
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = task.isCompleted,
                    onCheckedChange = { onCompleteTask() },
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f),
                    textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                )

                IconButton(
                    onClick = onDeleteTask,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Eliminar tarea",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Descripción
            if (!task.description.isNullOrEmpty()) {
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 32.dp))

                Spacer(modifier = Modifier.height(4.dp))
            }

            // Prioridad y Fecha
            Row(
                modifier = Modifier.padding(start = 32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Icono de prioridad
                Icon(
                    imageVector = when (task.priority) {
                        Task.Priority.URGENTE -> Icons.Default.Warning
                        Task.Priority.IMPORTANTE -> Icons.Default.Info
                        Task.Priority.POR_HACER -> Icons.Default.Info
                        Task.Priority.IRRELEVANTE -> Icons.Default.Info
                    },
                    contentDescription = "Prioridad",
                    tint = when (task.priority) {
                        Task.Priority.URGENTE -> Color.Red
                        Task.Priority.IMPORTANTE -> Color.Yellow
                        Task.Priority.POR_HACER -> Color.Yellow
                        Task.Priority.IRRELEVANTE -> Color.Green
                    },
                    modifier = Modifier.size(16.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Fecha límite
                Text(
                    text = "Vence: ${SimpleDateFormat("dd/MM/yyyy").format(task.dueDate)}",
                    style = MaterialTheme.typography.labelSmall,
                    color = if (task.dueDate.before(Date())) Color.Red else MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
