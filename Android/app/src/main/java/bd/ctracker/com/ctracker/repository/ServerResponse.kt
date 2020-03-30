package bd.ctracker.com.ctracker.repository

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ServerResponse(
    @SerializedName("status") val status: Int = 0,
    @SerializedName("error") val error: String = "",
    @SerializedName("error_type") val errorType: String = "",
    @Expose(deserialize = false) // deserialize is this filed is not required
    @SerializedName("result") val result: String = ""
)