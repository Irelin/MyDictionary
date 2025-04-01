package com.example.words_data.models.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class ExerciseDBO(@PrimaryKey(autoGenerate = true) val exerciseId: Int,
    val type: ExerciseType,
    val succeedCount: Int) {
}

enum class ExerciseType() {
    SHOW_TRANSLATION,
    WRITE_TRANSLATION
}