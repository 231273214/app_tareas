package com.example.app.lareas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.lareas.repository.TareaRepository
import com.example.app_tareas.database.entities.Tarea
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TareaViewModel @Inject constructor(
    private val repositorio: TareaRepository
) : ViewModel() {
    val tareas: Flow<List<Tarea>> = repositorio.obtenerTareas()

    fun insertarTarea(tarea: Tarea) = viewModelScope.launch {
        repositorio.insertarTarea(tarea)
    }

    fun eliminarTarea(tarea: Tarea) = viewModelScope.launch {
        repositorio.eliminarTarea(tarea)
    }
}

