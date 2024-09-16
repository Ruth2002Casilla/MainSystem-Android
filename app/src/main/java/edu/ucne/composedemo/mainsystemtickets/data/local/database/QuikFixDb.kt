package edu.ucne.composedemo.mainsystemtickets.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.composedemo.mainsystemtickets.data.local.dao.PrioridadDao
import edu.ucne.composedemo.mainsystemtickets.data.local.dao.SistemaDao
import edu.ucne.composedemo.mainsystemtickets.data.local.entities.PrioridadEntity
import edu.ucne.composedemo.mainsystemtickets.data.local.entities.SistemaEntity

@Database(
    entities = [
        PrioridadEntity::class,
        SistemaEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class QuikFixDb : RoomDatabase() {
    abstract fun prioridadDao(): PrioridadDao
    abstract fun sistemaDao(): SistemaDao
}