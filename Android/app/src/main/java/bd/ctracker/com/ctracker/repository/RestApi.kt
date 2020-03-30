package bd.ctracker.com.ctracker.repository

import bd.ctracker.com.ctracker.model.CTEventInfo
import bd.ctracker.com.ctracker.model.CTUserInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestApi {

    @Headers("Content-Type: application/json")
    @POST("users")
    fun addUser(@Body userData: CTUserInfo): Call<ServerResponse>

    @Headers("Content-Type: application/json")
    @POST("events")
    fun addEvent(@Body eventData: CTEventInfo): Call<ServerResponse>
}