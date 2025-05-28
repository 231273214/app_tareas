package com.example.app_tareas.database.dao

import androidx.room.*
import com.example.app_tareas.database.entities.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY
            CASE priority
            WHEN 'URGENTE' THEN 1
            WHEN 'IMPORTANTE' THEN 2
            WHEN 'TOCA_HACERLA' THEN 3
            ELSE 4
            END, dueDate ASC")
        fun getAllTasks(): Flow<List<Task>>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertTask(task: Task)

        @Update
        suspend fun updateTask(task: Task)

        @Delete
        suspend fun deleteTask(task: Task)

        @Query("SELECT * FROM tasks WHERE title LIKE :query OR description LIKE :query OR group LIKE :query")
        fun searchTasks(query: String): Flow<List<Task>>
}