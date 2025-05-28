package com.example.app_tareas.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.app_tareas.database.dao.TaskDao
import com.example.app_tareas.database.entities.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        const val DATABASE_NAME = "tasks_db"
    }
}