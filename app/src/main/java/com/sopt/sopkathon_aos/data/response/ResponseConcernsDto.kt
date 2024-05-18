package com.sopt.sopkathon_aos.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseConcernsDto(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: Data,
) {
    @Serializable
    data class Data(
        @SerialName("concernId")
        val concernId: Long,
    )
}
