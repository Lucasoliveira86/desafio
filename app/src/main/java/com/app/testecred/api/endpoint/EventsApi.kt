package com.app.testecred.api.endpoint

import com.app.testecred.api.model.Events
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

const val BASE_URL = "http://5f5a8f24d44d640016169133.mockapi.io/api/"

interface EventsApi {

    @GET("events")
    fun getEvents() : Call<List<Events>>

    companion object {
        operator fun invoke() : EventsApi{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EventsApi::class.java)
        }
    }

    @POST("checkin")
    fun postCheckin(
        @Path(value = "id") eventId: String?,
        @Body requestBody: RequestBody?,

    ): Call<Events?>?
}