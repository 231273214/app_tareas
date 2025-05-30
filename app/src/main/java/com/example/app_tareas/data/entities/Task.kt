package com.example.app_tareas.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority,
    @ColumnInfo(name = "task_group") // Cambia el nombre de la columna en la BD
    val group: String,
    val creationDate: Date,
    val dueDate: Date,
    val completionDate: Date?,
    val isCompleted: Boolean = false
) {
    enum class Priority {
        URGENTE, IMPORTANTE, POR_HACER, IRRELEVANTE
    }
}