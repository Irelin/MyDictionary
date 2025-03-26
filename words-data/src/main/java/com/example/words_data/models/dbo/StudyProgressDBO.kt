package com.example.words_data.models.dbo

import androidx.room.Entity

@Entity(tableName = "study_progress")
data class StudyProgressDBO(val exerciseType: ExerciseType, val succeedExercises: Int)

enum class ExerciseType(val number: Int, val succeedCount: Int) {
    SHOW_TRANSLATION(1, 5),
    WRITE_TRANSLATION(2, 3)
}
