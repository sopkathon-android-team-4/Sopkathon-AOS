package com.sopt.sopkathon_aos.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object RetrofitFactory {
    private const val BASE_URL = ""
    private const val CONTENT_TYPE = "application/json"
    private val json: Json = Json {
        ignoreUnknownKeys = true
    }

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory(CONTENT_TYPE.toMediaType()))
        .build()

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}