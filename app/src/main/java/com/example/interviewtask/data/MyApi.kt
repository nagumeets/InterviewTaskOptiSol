package com.example.interviewtask.data

import com.example.interviewtask.data.models.Feature
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {

    @GET("v2/locations/markers?")
    suspend fun getData(
            @Query("proximity") proximity: String,
            @Query("proximity_square") proximity_square: Int,
    ): Feature


    companion object {

        private const val BASE_URL = "https://bikewise.org/api/"

        operator fun invoke(): MyApi = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClient.Builder().also { client ->
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
    }
}