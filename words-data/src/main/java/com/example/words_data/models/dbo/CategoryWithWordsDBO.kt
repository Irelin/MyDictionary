package com.example.words_data.models.dbo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

data class CategoryWithWordsDBO(
    @Embedded val category: CategoryDBO,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "wordId",
        associateBy = Junction(CategoryWordCrossRef::class)
    )
    val words: List<WordDBO>
)

@Entity(primaryKeys = ["categoryId", "wordId"])
data class CategoryWordCrossRef(
    val categoryId: Long,
    val wordId: Long
)
