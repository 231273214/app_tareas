package com.example.app.lareas.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.app_tareas.database.entities.Tarea

@Composable
fun TareaItem(
    tarea: Tarea,  // Cambiado de Int a Tarea
    onDelete: (Tarea) -> Unit
) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = tarea.titulo)
            Text(text = "Prioridad: ${tarea.prioridad}")
            Button(
                onClick = { onDelete(tarea) },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Eliminar")
            }
        }
    }
}