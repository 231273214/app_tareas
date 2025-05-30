package com.example.app_tareas.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.app_tareas.data.database.TaskDao
import com.example.app_tareas.data.entities.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.Date
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.material.icons.filled.Delete
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
class TaskViewModelFactory(private val taskDao: TaskDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel(taskDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {
    val tasks: Flow<List<Task>> = taskDao.getAllTasks()
    val pendingTasks: Flow<List<Task>> = taskDao.getPendingTasks()
    val allGroups: Flow<List<String>> = taskDao.getAllGroups()

    fun searchTasks(query: String): Flow<List<Task>> = taskDao.searchTasks("%$query%")

    fun addTask(
        title: String,
        description: String,
        priority: Task.Priority,
        group: String,
        dueDate: Date
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val task = Task(
                title = title,
                description = description,
                priority = priority,
                group = group,
                creationDate = Date(),
                dueDate = dueDate,
                completionDate = null
            )
            taskDao.insertTask(task)
        }
    }

    fun getTaskById(taskId: Int): Flow<Task?> {
        return taskDao.getTaskById(taskId)
            .catch { e ->
                Log.e("VIEWMODEL", "Error al obtener tarea: ${e.message}")
                emit(null)
            }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                taskDao.updateTask(task)
            } catch (e: Exception) {
                Log.e("VIEWMODEL", "Error al actualizar: ${e.message}")
                throw e
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.deleteTask(task)
        }
    }

    fun completeTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.updateTask(task.copy(isCompleted = true))
        }
    }

    fun deleteCompletedTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.deleteCompletedTasks()
        }
    }
}