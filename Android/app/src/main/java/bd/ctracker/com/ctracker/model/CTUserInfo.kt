package bd.ctracker.com.ctracker.model

import com.google.gson.annotations.SerializedName


data class CTUserInfo(

    @SerializedName("userId")
    val id: Int?,

    @SerializedName("userName")
    val name: String?,

    @SerializedName("userPhoneNumber")
    val phoneNumber: String?,

    @SerializedName("userNid")
    val nationalID: String?,

    @SerializedName("userDuid")
    val userDuid: String?
)