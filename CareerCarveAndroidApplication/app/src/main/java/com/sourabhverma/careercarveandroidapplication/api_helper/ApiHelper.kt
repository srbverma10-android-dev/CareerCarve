package com.sourabhverma.careercarveandroidapplication.api_helper

import com.sourabhverma.careercarveandroidapplication.entry_point.data.remote.dto.AddMentor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


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

    companion object{
        operator fun invoke() : ApiHelper {
            return Retrofit.Builder()
                .baseUrl("http://192.168.176.216:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiHelper::class.java)
        }
    }
}