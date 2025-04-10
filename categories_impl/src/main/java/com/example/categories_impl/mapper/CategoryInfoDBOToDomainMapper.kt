package com.example.categories_impl.mapper

import com.example.categories_api.domain.models.Category
import com.example.core_data.dbo.CategoryInfoDBO
import javax.inject.Inject

class CategoryInfoDBOToDomainMapper @Inject constructor() {
    fun map(categoryInfoDBO: CategoryInfoDBO) =
        categoryInfoDBO.let { Category(it.categoryId, it.name, it.wordsCount) }
}