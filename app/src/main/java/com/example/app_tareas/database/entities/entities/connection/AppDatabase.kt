package com.example.app_tareas.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.app.lareas.database.dao.TareaDao
import com.example.app_tareas.database.converters.Converters
import com.example.app_tareas.database.entities.Tarea

@Database(
    entities = [Tarea::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tareaDao(): TareaDao
}