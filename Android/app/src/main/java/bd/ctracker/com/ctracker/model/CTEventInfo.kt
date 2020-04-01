package bd.ctracker.com.ctracker.model

import com.google.gson.annotations.SerializedName

data class CTEventInfo(
    @SerializedName("eventId")
    val eventId: Int?,

    @SerializedName("userId")
    val userId: Int?,

    @SerializedName("latitude")
    val latitude: Double?,

    @SerializedName("longitude")
    val longitude: Double?,

    @SerializedName("altitude")
    val altitude: Double?
)