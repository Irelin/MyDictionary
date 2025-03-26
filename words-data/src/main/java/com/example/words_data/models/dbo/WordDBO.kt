package com.example.words_data.models.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class WordDBO(
    @PrimaryKey(autoGenerate = true) val wordId: Long,
    val originValue: String,
    val translateValue: String
)
