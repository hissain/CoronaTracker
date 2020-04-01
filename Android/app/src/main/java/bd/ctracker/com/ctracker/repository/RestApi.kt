package bd.ctracker.com.ctracker.repository

import bd.ctracker.com.ctracker.model.CTEventInfo
import bd.ctracker.com.ctracker.model.CTUserInfo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestApi {

    @Headers("Content-Type: application/json")
    @POST("users")
    fun addUser(@Body userData: CTUserInfo): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @POST("events")
    fun addEvent(@Body eventData: CTEventInfo): Call<ResponseBody>
}