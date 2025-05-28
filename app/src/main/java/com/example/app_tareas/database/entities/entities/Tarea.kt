package com.example.app_tareas.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,
    val description: String,
    val group: String,
    val priority: TaskPriority,
    val creationDate: LocalDateTime,
    val dueDate: LocalDate,
    val completionDate: LocalDate? = null,
    val isCompleted: Boolean = false
)

enum class TaskPriority {
    URGENTE, IMPORTANTE, TOCA_HACERLA, NO_TAN_IMPORTANTE;

    companion object {
        fun fromString(value: String): TaskPriority {
            return values().first { it.name == value }
        }
    }
}