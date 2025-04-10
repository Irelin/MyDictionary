package com.example.core_data.dbo

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

@Entity(primaryKeys = ["categoryId", "wordId"], tableName = "categories_words")
data class CategoryWordCrossRef(
    val categoryId: Long,
    val wordId: Long
)
