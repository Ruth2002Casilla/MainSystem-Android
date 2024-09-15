package edu.ucne.composedemo.mainsystemtickets.data.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.composedemo.mainsystemtickets.data.local.database.QuikFixDb
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideTicketingDB(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            QuikFixDb::class.java,
            "QuikfixDB.db"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providePrioridadDao(quikFixDb: QuikFixDb) = quikFixDb.prioridadDao()


}