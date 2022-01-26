package com.inmersoft.room.hilt.di

import android.content.Context
import androidx.room.Room
import com.inmersoft.room.WordDao
import com.inmersoft.room.WordRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): WordRoomDatabase {
        return Room.databaseBuilder(
            appContext,
            WordRoomDatabase::class.java,
            "logging.db"
        ).build()
    }

    @Provides
    fun provideLogDao(database: WordRoomDatabase): WordDao {
        return database.wordDao()
    }
}