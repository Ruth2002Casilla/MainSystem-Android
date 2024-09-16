package edu.ucne.composedemo.mainsystemtickets.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.composedemo.mainsystemtickets.data.local.entities.ClienteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClienteDao {
    @Upsert()
    suspend fun save(cliente: ClienteEntity)

    @Query("SELECT * FROM Clientes WHERE nombre = :nombre LIMIT 1")
    suspend fun findByNombre(nombre: String): ClienteEntity?

    @Query(
        """
        SELECT * 
        FROM Clientes 
        WHERE clienteId=:id  
        LIMIT 1
        """
    )
    suspend fun find(id: Int): ClienteEntity?

    @Delete
    suspend fun delete(da: ClienteEntity)

        @Query("SELECT * FROM Clientes")
    fun getAll(): Flow<List<ClienteEntity>>

}