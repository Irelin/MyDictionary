package com.example.words_data.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.words_data.dao.CategoriesDao
import com.example.words_data.dao.WordsDao
import com.example.words_data.models.dbo.CategoryDBO
import com.example.words_data.models.dbo.WordDBO

@Database(entities = [WordDBO::class, CategoryDBO::class], version = 1)
abstract class WordsDatabase : RoomDatabase() {
    abstract fun wordsDao(): WordsDao
    abstract fun categoriesDao(): CategoriesDao
}

fun WordsDatabase(context: Context): WordsDatabase {
    return Room.databaseBuilder(
        checkNotNull(context.applicationContext),
        WordsDatabase::class.java,
        "words"
    ).build()
}