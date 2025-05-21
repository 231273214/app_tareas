package com.example.app_tareas.database.entities.entities

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(tableName = "Tareas")
data class Task(
    @PrimaryKey(autoGenerate = false)
    val id: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "task_group")
    var group: String,

    @ColumnInfo(name = "priority")
    var priority: TaskPriority,

    @ColumnInfo(name = "creation_date")
    val creationDate: LocalDateTime,

    @ColumnInfo(name = "due_date")
    var dueDate: LocalDate,

    @ColumnInfo(name = "completion_date")
    var completionDate: LocalDate? = null,

    @ColumnInfo(name = "is_completed")
    var isCompleted: Boolean = false
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun complete() {
        isCompleted = true
        completionDate = LocalDate.now()
        priority = TaskPriority.FINALIZADA
    }

    fun updatePriority(newPriority: TaskPriority) {
        if (!isCompleted) {
            priority = newPriority
        }
    }
}

enum class TaskPriority {
    URGENTE,
    IMPORTANTE,
    TOCA_HACERLA,
    NO_TAN_IMPORTANTE,
    FINALIZADA;

    companion object {
        fun fromString(value: String): TaskPriority {
            return values().first { it.name == value }
        }
    }
}