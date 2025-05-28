package com.example.app_tareas.data.database

import androidx.room.*
import com.example.app_tareas.data.entities.Task
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface TaskDao {
    // Operaciones CRUD básicas
    @Insert
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    // Consultas específicas
    @Query("SELECT * FROM tasks ORDER BY priority ASC, dueDate ASC")
    fun getAllTasks(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE isCompleted = 0 ORDER BY priority ASC, dueDate ASC")
    fun getPendingTasks(): Flow<List<Task>>

    @Query("SELECT DISTINCT task_group FROM tasks")
    fun getAllGroups(): Flow<List<String>>

    @Query("SELECT * FROM tasks WHERE title LIKE :query OR description LIKE :query OR task_group LIKE :query")
    fun searchTasks(query: String): Flow<List<Task>>
}