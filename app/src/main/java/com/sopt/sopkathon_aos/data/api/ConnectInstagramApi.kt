package com.sopt.sopkathon_aos.data.api

import ResponseLuckyDto
import retrofit2.http.GET
import retrofit2.http.Header

interface ConnectInstagramApi {
    @GET("answers")
    suspend fun getLuckyContent(
        @Header("concernId") concernId: Int, // Header에 concernId 전달
    ): ResponseLuckyDto
}
