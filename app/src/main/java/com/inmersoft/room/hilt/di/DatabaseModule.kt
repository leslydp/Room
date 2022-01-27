package com.inmersoft.room.hilt.di

import android.content.Context
import androidx.room.Room
import com.inmersoft.room.data.source.local.dao.WordDao
import com.inmersoft.room.data.source.local.WordRoomDatabase
import com.inmersoft.room.data.source.repository.WordRepository
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
        return WordRoomDatabase.getDatabase(
            appContext
        )
    }

    @Provides
    @Singleton
    fun providesRepository(wordDao: WordDao): WordRepository {
        return WordRepository(wordDao = wordDao)
    }

    @Provides
    fun provideLogDao(database: WordRoomDatabase): WordDao {
        return database.wordDao()
    }
}