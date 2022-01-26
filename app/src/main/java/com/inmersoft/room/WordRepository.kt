package com.inmersoft.room

import androidx.annotation.WorkerThread
import com.inmersoft.room.Word
import com.inmersoft.room.WordDao
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class WordRepository(private val wordDao: WordDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()



    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}