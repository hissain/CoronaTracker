package bd.ctracker.com.ctracker.repository

import bd.ctracker.com.ctracker.model.CTEventInfo
import bd.ctracker.com.ctracker.model.CTUserInfo
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber


object ServiceBuilder {
    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.64.1:8080/") // change this IP for testing by your actual machine IP
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}

class RestApiService {

    fun addUser(userData: CTUserInfo, onResult: (CTUserInfo?) -> Unit){

        val retrofit = ServiceBuilder.buildService(RestApi::class.java)

        retrofit.addUser(userData).enqueue(
            object : Callback<CTUserInfo> {
                override fun onFailure(call: Call<CTUserInfo>, t: Throwable) {
                    Timber.d("Failure: %s", t.stackTrace)
                }
                override fun onResponse( call: Call<CTUserInfo>, response: Response<CTUserInfo>) {
                    Timber.d("Success with response code: %s", response.code())
                    val addedUser = response.body()
                    Timber.d("Added user: %s", addedUser)
                    onResult(addedUser)
                }
            }
        )
    }

    fun addEvent(eventData: CTEventInfo, onResult: (CTEventInfo?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addEvent(eventData).enqueue(
            object : Callback<CTEventInfo> {
                override fun onFailure(call: Call<CTEventInfo>, t: Throwable) {
                    Timber.d("Failure: %s", t.stackTrace)
                }
                override fun onResponse( call: Call<CTEventInfo>, response: Response<CTEventInfo>) {
                    Timber.d("Success with response code: %s", response.code())
                    Timber.d("Success with response code: %s", response.code())
                    val addedEvent = response.body()
                    Timber.d("Added user: %s", addedEvent)
                    onResult(addedEvent)
                }
            }
        )
    }
}