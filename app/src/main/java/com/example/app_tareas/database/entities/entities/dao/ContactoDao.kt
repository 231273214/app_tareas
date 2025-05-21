package com.example.androidroom.database.entities.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.androidroom.database.entities.Contacto
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactoDao {
    @Query("select * from contactos order by nombre asc")
    fun all(): Flow<List<Contacto>>

    @Query("select * from contactos where id=:id")
    fun findByID(id:Int): Contacto
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun save(newContacto: Contacto)

    @Update
    fun update(vararg contacto: Contacto)

    @Delete
    fun delete(vararg contacto: Contacto)
}