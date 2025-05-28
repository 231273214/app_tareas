package com.example.app_tareas.data

import com.example.app_tareas.database.dao.TaskDao
import com.example.app_tareas.database.entities.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao  // Inyecta el DAO
) {
    // Obtener todas las tareas ordenadas por prioridad y fecha
    fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()

    // Buscar tareas por texto
    fun searchTasks(query: String): Flow<List<Task>> = taskDao.searchTasks("%$query%")

    // Operaciones CRUD
    suspend fun insertTask(task: Task) = taskDao.insertTask(task)
    suspend fun updateTask(task: Task) = taskDao.updateTask(task)
    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)
}