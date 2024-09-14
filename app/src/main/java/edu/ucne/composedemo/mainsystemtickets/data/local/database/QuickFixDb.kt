package edu.ucne.composedemo.mainsystemtickets.data.local.database

import androidx.room.Database
import androidx.room.Room
import android.content.Context
import androidx.room.RoomDatabase
import edu.ucne.composedemo.mainsystemtickets.data.local.dao.ClienteDao
import edu.ucne.composedemo.mainsystemtickets.data.local.dao.PrioridadDao
import edu.ucne.composedemo.mainsystemtickets.data.local.dao.SistemaDao
import edu.ucne.composedemo.mainsystemtickets.data.local.dao.TicketDao
import edu.ucne.composedemo.mainsystemtickets.data.local.entity.ClienteEntity
import edu.ucne.composedemo.mainsystemtickets.data.local.entity.PrioridadEntity
import edu.ucne.composedemo.mainsystemtickets.data.local.entity.SistemaEntity
import edu.ucne.composedemo.mainsystemtickets.data.local.entity.TicketEntity

@Database(
    entities = [
        PrioridadEntity::class,
        SistemaEntity::class,
        ClienteEntity::class,
        TicketEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class QuickFixDb : RoomDatabase() {

    abstract fun prioridadDao(): PrioridadDao
    abstract fun sistemaDao(): SistemaDao
    abstract fun clienteDao(): ClienteDao
    abstract fun ticketDao(): TicketDao

    companion object {
        @Volatile
        private var INSTANCE: QuickFixDb? = null
        private const val DATABASE_NAME = "quickFixDb"

        fun getDatabase(context: Context): QuickFixDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuickFixDb::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
