package com.inmersoft.room.data.source.repository

import com.inmersoft.room.data.source.local.entity.Word
import com.inmersoft.room.data.source.local.dao.WordDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class WordRepository(private val wordDao: WordDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    suspend fun insert(word: Word) {
        withContext(Dispatchers.IO) {
            wordDao.insert(word)
        }
    }
}