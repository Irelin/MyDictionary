package com.example.core_data.network

import com.example.core_data.network.utils.Constants
import com.example.core_data.network.utils.TimeApiKeyInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.POST
import retrofit2.http.Query

interface TranslateApi {
    @POST("translate")
    suspend fun translate(
        @Query("sourceLanguageCode") sourceLanguage: String,
        @Query("targetLanguageCode") targetLanguage: String,
        @Query("format") format: String = Constants.TRANSLATE_TEXT_FORMAT,
        @Query("texts") texts: Array<String>
    )
}

fun TranslateApi(baseUrl: String, apiKey: String, okHttpClient: OkHttpClient? = null, json: Json = Json): TranslateApi {
    return retrofit(baseUrl, apiKey, okHttpClient, json).create(TranslateApi::class.java)
}

private fun retrofit(baseUrl: String, apiKey: String, okHttpClient: OkHttpClient?, json: Json) : Retrofit {
    val modifiedOkHttpClient = (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
        .addInterceptor(TimeApiKeyInterceptor(apiKey)).build()
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(modifiedOkHttpClient)
        .addConverterFactory(json.asConverterFactory(Constants.CONTENT_TYPE.toMediaType()))
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .build()
}