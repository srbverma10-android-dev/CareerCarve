package com.sourabhverma.careercarveandroidapplication.add_schedule.data.remote.repository

import android.util.Log
import com.sourabhverma.careercarveandroidapplication.add_schedule.data.remote.dto.AddMeeting
import com.sourabhverma.careercarveandroidapplication.add_schedule.data.remote.dto.ScheduleSuggestion
import com.sourabhverma.careercarveandroidapplication.api_helper.ApiHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddScheduleRepo {

    private val apiHandler: ApiHelper = ApiHelper()

    fun addMeeting(student_id : Int, mentor_id : Int, student_name : String, mentor_name : String,
        student_email: String, mentor_email: String, duration : Int, meeting_date : String, schedule_start : String, schedule_end : String
        , onResult: (AddMeeting?) -> Unit) {
        apiHandler.addMeeting(student_email, mentor_email, student_name, mentor_name, duration,
        student_id, mentor_id, meeting_date, schedule_start, schedule_end)?.enqueue(object : Callback<AddMeeting?> {
            override fun onResponse(call: Call<AddMeeting?>, response: Response<AddMeeting?>) {
                if (response.code() == 200){
                    Log.d("CareerCarve", "onResponse: in if${response.body()}")
                    onResult(response.body())
                } else {
                    Log.d("CareerCarve", "onResponse: in else 1")
                    onResult(null)
                }
            }

            override fun onFailure(call: Call<AddMeeting?>, t: Throwable) {
                onResult(null)
            }

        })
    }



    fun searchMentorById(id : Int, onResult: (ScheduleSuggestion?) -> Unit) {
        apiHandler.searchMentorById(id)?.enqueue(object : Callback<ScheduleSuggestion?> {
            override fun onResponse(
                call: Call<ScheduleSuggestion?>,
                response: Response<ScheduleSuggestion?>
            ) {
                if (response.code() == 200){
                    Log.d("CareerCarve", "onResponse: in if${response.body()}")
                    onResult(response.body())
                } else {
                    Log.d("CareerCarve", "onResponse: in else 1")
                    onResult(null)
                }
            }

            override fun onFailure(call: Call<ScheduleSuggestion?>, t: Throwable) {
                Log.d("CareerCarve", "onResponse: in else 2 ${t.message}")
                onResult(null)
            }
        })
    }


    fun scheduleMeetingSuggestions(day : Int, area_of_intrest : Int,
                  onResult: (ScheduleSuggestion?)->Unit){
        apiHandler.scheduleMeetingSuggestions(day, area_of_intrest)?.enqueue(object :
            Callback<ScheduleSuggestion?> {
            override fun onResponse(
                call: Call<ScheduleSuggestion?>,
                response: Response<ScheduleSuggestion?>
            ) {
                if (response.code() == 200){
                    Log.d("CareerCarve", "onResponse: in if${response.body()}")
                    onResult(response.body())
                } else {
                    Log.d("CareerCarve", "onResponse: in else 1")
                    onResult(null)
                }
            }

            override fun onFailure(call: Call<ScheduleSuggestion?>, t: Throwable) {
                Log.d("CareerCarve", "onResponse: in else 2 ${t.message}")
                onResult(null)
            }
        })
    }
}