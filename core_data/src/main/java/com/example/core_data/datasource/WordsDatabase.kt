package com.example.core_data.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.core_data.dao.CategoriesDao
import com.example.core_data.dao.ExercisesDao
import com.example.core_data.dao.WordsDao
import com.example.core_data.dbo.CategoryDBO
import com.example.core_data.dbo.CategoryInfoDBO
import com.example.core_data.dbo.CategoryWordCrossRef
import com.example.core_data.dbo.ExerciseDBO
import com.example.core_data.dbo.WordDBO

@Database(entities = [WordDBO::class, CategoryDBO::class, ExerciseDBO::class, CategoryWordCrossRef::class], views =[CategoryInfoDBO::class], version = 1)
abstract class WordsDatabase : RoomDatabase() {
    abstract fun wordsDao(): WordsDao
    abstract fun categoriesDao(): CategoriesDao
    abstract fun exercisesDao(): ExercisesDao
}

fun WordsDatabase(context: Context): WordsDatabase {
    return Room.databaseBuilder(
        checkNotNull(context.applicationContext),
        WordsDatabase::class.java,
        "words"
    ).addCallback(object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            // todo: prepopulate exercises table
            // https://hadiyarajesh.hashnode.dev/pre-populating-room-database-with-static-data-in-android-using-hilt-di
        }
    }).build()
}