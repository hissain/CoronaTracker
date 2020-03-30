package bd.ctracker.com.ctracker.service

import bd.ctracker.com.ctracker.model.CTEventInfo
import bd.ctracker.com.ctracker.model.CTUserInfo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


object ServiceBuilder {
    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://api.server.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}


data class ServerResponse (
    @SerializedName("status") val status: Int = 0,
    @SerializedName("error") val error: String = "",
    @SerializedName("error_type") val errorType: String = "",
    @Expose(deserialize = false) // deserialize is this filed is not required
    @SerializedName("result") val result: String = ""
)

interface RestApi {

    @Headers("Content-Type: application/json")
    @POST("users")
    fun addUser(@Body userData: CTUserInfo): Call<ServerResponse>

    @Headers("Content-Type: application/json")
    @POST("events")
    fun addEvent(@Body eventData: CTEventInfo): Call<ServerResponse>
}

class RestApiService {

    fun addUser(userData: CTUserInfo){

        val retrofit = ServiceBuilder.buildService(RestApi::class.java)

        retrofit.addUser(userData).enqueue(
            object : Callback<ServerResponse> {

                override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
                    // failure
                }

                override fun onResponse( call: Call<ServerResponse>, response: Response<ServerResponse>) {

                    if (response.code() == 201) {
                        // user added
                    } else{
                        //user could not be added
                    }
                }
            }
        )
    }

    fun addEvent(eventData: CTEventInfo){

        val retrofit = ServiceBuilder.buildService(RestApi::class.java)

        retrofit.addEvent(eventData).enqueue(
            object : Callback<ServerResponse> {

                override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
                    // failure
                }

                override fun onResponse( call: Call<ServerResponse>, response: Response<ServerResponse>) {

                    if (response.code() == 201) {
                        // event added
                    } else{
                        //event could not be added
                    }
                }
            }
        )
    }
}