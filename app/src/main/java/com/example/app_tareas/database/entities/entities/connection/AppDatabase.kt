package com.example.app_tareas.database.entities.entities.connection

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.app_tareas.database.entities.entities.Tarea

@Database(
    entities = [Tarea::class],
    version = 1,
)

abstract class AppDatabase: RoomDatabase(){
    abstract fun Tarea(): Tarea
}