package com.example.core_data.network.models

import kotlinx.serialization.SerialName

data class TranslateResponse(@SerialName("translations") val translations: List<TranslatedText>)

data class TranslatedText(
    @SerialName("text") val text: String,
    @SerialName("detectedLanguageCode") val language: String
)