import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLuckyDto(
    @SerialName("status")
    val code: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: LuckyData,
)

@Serializable
data class LuckyData(
    @SerialName("content")
    val content: String
)
