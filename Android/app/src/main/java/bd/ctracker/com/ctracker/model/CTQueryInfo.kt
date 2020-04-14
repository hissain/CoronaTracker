package bd.ctracker.com.ctracker.model

import com.google.gson.annotations.SerializedName


data class CTQueryInfo(

    @SerializedName("userId")
    val id: Int?,

    @SerializedName("maxContactDistance")
    val maxContactDistance: Int?,

    @SerializedName("maxDateCount")
    val maxDateCount: Int?,

    @SerializedName("accessToken")
    val accessToken: String?
)