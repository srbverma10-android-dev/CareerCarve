package com.sourabhverma.careercarveandroidapplication.api_helper

import com.sourabhverma.careercarveandroidapplication.add_schedule.data.remote.dto.ScheduleSuggestion
import com.sourabhverma.careercarveandroidapplication.entry_point.data.remote.dto.AddMentor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor





interface ApiHelper {
    @FormUrlEncoded
    @POST("api/v1/mentors/add")
    fun addMentor(
        @Field("mentor_name") mentor_name : String,
        @Field("mentor_email") mentor_email : String,
        @Field("area_of_interst") area_of_interst : Int,
        @Field("avail_mon") avail_mon : Int,
        @Field("avail_tue") avail_tue : Int,
        @Field("avail_wed") avail_wed : Int,
        @Field("avail_thrus") avail_thrus : Int,
        @Field("avail_fri") avail_fri : Int,
        @Field("avail_sat") avail_sat : Int,
        @Field("avail_sun") avail_sun : Int,
        @Field("schedule_start") schedule_start : String,
        @Field("schedule_end") schedule_end : String
    ) : Call<AddMentor?>?

    @GET("api/v1/mentors/schedule/suggestion/{day}/{area_of_intrest}")
    fun scheduleMeetingSuggestions(
        @Path("day") day : Int,
        @Path("area_of_intrest") area_of_intrest : Int
    ) : Call<ScheduleSuggestion?>?

    companion object{

        operator fun invoke() : ApiHelper {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.176.216:8000/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiHelper::class.java)
        }
    }
}