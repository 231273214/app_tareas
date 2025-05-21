package com.example.app_tareas.database.entities.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tareas")
data class Tarea(
    @PrimaryKey val id: Int,
    @ColumnInfo val prioridad: String,
    @ColumnInfo val fecha: Int

    )
