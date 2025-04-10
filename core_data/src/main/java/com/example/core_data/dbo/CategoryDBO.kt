package com.example.core_data.dbo

import androidx.room.DatabaseView
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryDBO(
    @PrimaryKey(autoGenerate = true) val categoryId: Long,
    val name: String
)

@DatabaseView(
    "SELECT categories.categoryId, categories.name, " +
            "(SELECT COUNT(*) FROM categories_words WHERE categories.categoryId = categories_words.categoryId) AS wordsCount " +
            "FROM categories " +
            "LEFT JOIN categories_words ON categories.categoryId = categories_words.categoryId " +
            "GROUP BY categories.categoryId"
)
data class CategoryInfoDBO(
    @PrimaryKey(autoGenerate = true) val categoryId: Long,
    val name: String,
    val wordsCount: Int
)
