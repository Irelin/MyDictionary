package com.example.words_impl.domain.usecase

import com.example.words_api.domain.models.CategoryWords
import com.example.words_api.domain.usecase.GetCategoryWords
import com.example.words_impl.repository.WordsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryWordsUseCase @Inject constructor(private val wordsRepository: WordsRepository): GetCategoryWords {
    override suspend fun invoke(categoryId: Long): Flow<CategoryWords> = wordsRepository.getCategoryWords(categoryId)
}