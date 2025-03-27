package com.example.words_data.mapper

import com.example.words_data.models.Word
import com.example.words_data.models.dbo.WordDBO
import javax.inject.Inject

class WordDBOToDomainMapper @Inject constructor() {
    fun map(wordDBO: WordDBO) = wordDBO.let { Word(it.wordId, it.originValue, it.translateValue) }
}