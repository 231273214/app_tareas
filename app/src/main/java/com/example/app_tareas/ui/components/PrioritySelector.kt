package com.example.app_tareas.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.app_tareas.data.entities.Task

@Composable
fun PrioritySelector(
    selectedPriority: Task.Priority,
    onPrioritySelected: (Task.Priority) -> Unit
) {
    Column {
        Text(
            text = "Prioridad",
            style = MaterialTheme.typography.labelMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Task.Priority.values().forEach { priority ->
                FilterChip(
                    selected = priority == selectedPriority,
                    onClick = { onPrioritySelected(priority) },
                    label = { Text(priority.name) },
                    modifier = Modifier.padding(end = 4.dp)
                )
            }
        }
    }
}