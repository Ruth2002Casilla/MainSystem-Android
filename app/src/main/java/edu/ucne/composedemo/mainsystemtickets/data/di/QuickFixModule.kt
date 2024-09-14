package edu.ucne.composedemo.mainsystemtickets.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.composedemo.mainsystemtickets.data.local.dao.ClienteDao
import edu.ucne.composedemo.mainsystemtickets.data.local.dao.PrioridadDao
import edu.ucne.composedemo.mainsystemtickets.data.local.dao.SistemaDao
import edu.ucne.composedemo.mainsystemtickets.data.local.dao.TicketDao
import edu.ucne.composedemo.mainsystemtickets.data.local.database.QuickFixDb
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object QuickFixModule {
    @Provides
    @Singleton
    fun provideQuickFixDb(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            QuickFixDb::class.java,
            "QuickFixDb.db"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providePrioridadDao(db: QuickFixDb): PrioridadDao = db.prioridadDao()

    @Provides
    fun provideSistemaDao(db: QuickFixDb): SistemaDao = db.sistemaDao()

    @Provides
    fun provideClienteDao(db: QuickFixDb): ClienteDao = db.clienteDao()

    @Provides
    fun provideTicketDao(db: QuickFixDb): TicketDao = db.ticketDao()

}


