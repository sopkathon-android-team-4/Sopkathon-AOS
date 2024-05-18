package com.sopt.sopkathon_aos.data.api

import com.sopt.sopkathon_aos.data.response.LuckyAnswerResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface LuckyAnswerApi {

    @GET("answers/{memberId}/list")
    suspend fun getLuckyAnswers(
        @Path("memberId") memberId: Long = 1,
    ): LuckyAnswerResponseDto
}
