package bd.ctracker.com.ctracker.repository

import bd.ctracker.com.ctracker.model.CTEventInfo
import bd.ctracker.com.ctracker.model.CTUserInfo
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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