package com.example.words_data.mapper

import com.example.words_data.models.Word
import com.example.words_data.models.dbo.WordDBO

class WordDBOToDomainMapper {
    fun map(wordDBO: WordDBO) = wordDBO.let { Word(it.wordId, it.originValue, it.translateValue) }
}