package com.example.app.lareas.repository

import com.example.app.lareas.database.dao.TareaDao
import com.example.app_tareas.database.entities.Tarea
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TareaRepository @Inject constructor(private val tareaDao: TareaDao) {
    fun obtenerTareas(): Flow<List<Tarea>> = tareaDao.obtenerTareas()
    suspend fun insertarTarea(tarea: Tarea) = tareaDao.insertarTarea(tarea)
    suspend fun actualizarTarea(tarea: Tarea) = tareaDao.actualizarTarea(tarea)
    suspend fun eliminarTarea(tarea: Tarea) = tareaDao.eliminarTarea(tarea)
    fun buscarTareas(query: String): Flow<List<Tarea>> = tareaDao.buscarTareas("%$query%")
}