package edu.ucne.composedemo.mainsystemtickets.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.composedemo.mainsystemtickets.data.local.dao.PrioridadDao
import edu.ucne.composedemo.mainsystemtickets.data.local.entities.PrioridadEntity

@Database(
    entities = [
        PrioridadEntity::class,
    ],
    version = 2,
    exportSchema = false
)
abstract class QuikFixDb : RoomDatabase() {
    abstract fun prioridadDao(): PrioridadDao
}