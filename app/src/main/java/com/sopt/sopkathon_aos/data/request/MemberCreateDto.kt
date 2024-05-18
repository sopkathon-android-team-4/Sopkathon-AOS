package com.sopt.sopkathon_aos.data.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MemberCreateDto(
    @SerialName("name")
    val name: String,
    @SerialName("age")
    val age: Int,
    @SerialName("mbti")
    val mbti: String,
)