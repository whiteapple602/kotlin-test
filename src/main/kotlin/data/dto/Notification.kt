package data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Notification (
    @SerialName("icon")
    val icon: String?,
    @SerialName("content")
    var content: String,
    @SerialName("time")
    var date: String
)

