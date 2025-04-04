package com.example.words_impl.mapper

import com.example.core_data.dbo.WordDBO
import com.example.words_api.domain.models.Word
import javax.inject.Inject

class WordDBOToDomainMapper @Inject constructor() {
    fun map(wordDBO: WordDBO) = wordDBO.let { Word(it.wordId, it.originValue, it.translateValue) }
}