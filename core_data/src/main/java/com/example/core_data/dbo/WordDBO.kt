package com.example.core_data.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class WordDBO(
    @PrimaryKey(autoGenerate = true) val wordId: Long,
    val originValue: String,
    val translateValue: String,
    val status: StudyStatus = StudyStatus.InProgress
)

enum class StudyStatus {
    InProgress,
    Studied
}
