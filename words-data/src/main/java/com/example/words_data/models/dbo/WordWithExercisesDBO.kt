package com.example.words_data.models.dbo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity(tableName = "study_progress")
data class WordWithExercises(
    @Embedded val word: WordDBO,
    @Relation(
        parentColumn = "wordId",
        entityColumn = "exerciseId",
        associateBy = Junction(WordExerciseCrossRef::class)
    )
    val exercises: List<ExerciseDBO>
)

@Entity(primaryKeys = ["playlistId", "songId"])
data class WordExerciseCrossRef(
    val wordId: Long,
    val exerciseId: Int
)


