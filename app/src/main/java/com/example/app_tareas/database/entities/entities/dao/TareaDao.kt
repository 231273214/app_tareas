package com.example.app.lareas.database.dao

import androidx.room.*
import com.example.app_tareas.database.entities.Tarea
import kotlinx.coroutines.flow.Flow

@Dao
interface TareaDao {
    @Query("""
        SELECT * FROM tareas 
        ORDER BY 
        CASE prioridad 
            WHEN 'URGENTE' THEN 1 
            WHEN 'IMPORTANTE' THEN 2 
            WHEN 'TOCA_HACERLA' THEN 3 
            ELSE 4 
        END, 
        fechaLimite ASC
    """)
    fun obtenerTareas(): Flow<List<Tarea>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTarea(tarea: Tarea)

    @Update
    suspend fun actualizarTarea(tarea: Tarea)

    @Delete
    suspend fun eliminarTarea(tarea: Tarea)

    @Query("SELECT * FROM tareas WHERE titulo LIKE :query OR descripcion LIKE :query")
    fun buscarTareas(query: String): Flow<List<Tarea>>
}