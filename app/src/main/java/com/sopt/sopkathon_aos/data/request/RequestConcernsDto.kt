package com.sopt.sopkathon_aos.data.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestConcernsDto(
    @SerialName("content")
    val content: String,
)
