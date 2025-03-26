package com.example.words_data.models.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryDBO(
    @PrimaryKey(autoGenerate = true) val categoryId: Long,
    val name: String)
