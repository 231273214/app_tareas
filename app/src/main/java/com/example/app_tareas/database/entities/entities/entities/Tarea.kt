package com.example.app_tareas.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDate
import java.util.Date

@Entity(tableName = "tareas")
data class Tarea(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val descripcion: String,
    val grupo: String,
    val prioridad: String,
    val fechaCreacion: Date,
    val fechaLimite: Date,
    val fechaFinalizacion: LocalDate? = null,
    val estaCompletada: Boolean = false
)

class PrioridadTarea {
    companion object {
        fun values() {
        }

        val TOCA_HACERLA: Any = TODO()
        val IMPORTANTE: Any
        val URGENTE: Any
    }


}

