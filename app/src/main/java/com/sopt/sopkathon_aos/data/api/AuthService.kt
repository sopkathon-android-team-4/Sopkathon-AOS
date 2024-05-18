package com.sopt.sopkathon_aos.data.api


import com.sopt.sopkathon_aos.data.request.MemberCreateDto
import com.sopt.sopkathon_aos.data.response.SuccessStatusResponse
import retrofit2.Call
import retrofit2.http.Body

import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/members")
    fun postMember(
        @Body request: MemberCreateDto,
    ): Call<SuccessStatusResponse>
}