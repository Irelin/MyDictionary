package com.example.categories_impl.repository

import com.example.categories_api.domain.models.Category
import com.example.categories_impl.mapper.CategoryInfoDBOToDomainMapper
import com.example.core_data.dao.CategoriesDao
import com.example.core_data.dbo.CategoryDBO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoriesRepository @Inject constructor(
    private val categoriesDao: CategoriesDao,
    private val categoriesMapper: CategoryInfoDBOToDomainMapper
) {
    suspend fun addNewCategory(name: String): Long {
        return categoriesDao.insert(CategoryDBO(0L, name))
    }

    suspend fun getAll(): Flow<List<Category>> {
        return categoriesDao.observeAllCategoriesInfo().map { it.map { category -> categoriesMapper.map(category) } }
            .catch { listOf<Category>() }
    }

    suspend fun getLast(count: Int): Flow<List<Category>> {
        return categoriesDao.observeLastCategoriesInfo(count).map { it.map { category -> categoriesMapper.map(category) } }
            .catch { listOf<Category>() }
    }
}