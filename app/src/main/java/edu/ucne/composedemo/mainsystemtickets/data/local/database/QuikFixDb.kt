package edu.ucne.composedemo.mainsystemtickets.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.composedemo.mainsystemtickets.data.local.dao.ClienteDao
import edu.ucne.composedemo.mainsystemtickets.data.local.dao.PrioridadDao
import edu.ucne.composedemo.mainsystemtickets.data.local.dao.SistemaDao
import edu.ucne.composedemo.mainsystemtickets.data.local.entities.ClienteEntity
import edu.ucne.composedemo.mainsystemtickets.data.local.entities.PrioridadEntity
import edu.ucne.composedemo.mainsystemtickets.data.local.entities.SistemaEntity

@Database(
    entities = [
        PrioridadEntity::class,
        SistemaEntity::class,
        ClienteEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class QuikFixDb : RoomDatabase() {
    abstract fun prioridadDao(): PrioridadDao
    abstract fun sistemaDao(): SistemaDao
    abstract fun clienteDao(): ClienteDao
}