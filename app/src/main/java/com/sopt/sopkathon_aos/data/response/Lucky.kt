package com.sopt.sopkathon_aos.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LuckyAnswerResponseDto(
    @SerialName("data")
    val `data`: List<Answer>,
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: Int,
) {
    @Serializable
    data class Answer(
        @SerialName("content")
        val content: String,
    )
}
