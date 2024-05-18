package com.sopt.sopkathon_aos.data.api

import com.sopt.sopkathon_aos.data.request.RequestConcernsDto
import com.sopt.sopkathon_aos.data.response.ResponseConcernsDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ConcernsPostApi {
    @POST("api/v1/concerns")
    fun postConcerns(
        @Header("memberId") memberId: Long = 1,
        @Body request: RequestConcernsDto,
    ): Call<ResponseConcernsDto>
}