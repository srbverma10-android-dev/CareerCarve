package com.sourabhverma.careercarveandroidapplication.dashboard.data.remote.repository

import android.util.Log
import com.sourabhverma.careercarveandroidapplication.api_helper.ApiHelper
import com.sourabhverma.careercarveandroidapplication.dashboard.data.remote.dto.MeetingList
import com.sourabhverma.careercarveandroidapplication.entry_point.data.remote.dto.AddStudent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardRepo {

    private val apiHandler: ApiHelper = ApiHelper()

    fun getMeetingsByMentorId(mentor_id : Int, onResult: (MeetingList?)->Unit) {
        apiHandler.getMeetingListByMentorId(mentor_id)?.enqueue(object : Callback<MeetingList?> {
            override fun onResponse(call: Call<MeetingList?>, response: Response<MeetingList?>) {
                if (response.code() == 200){
                    Log.d("CareerCarve", "onResponse: in if${response.body()}")
                    onResult(response.body())
                } else {
                    Log.d("CareerCarve", "onResponse: in else 1")
                    onResult(null)
                }
            }

            override fun onFailure(call: Call<MeetingList?>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun getMeetingsByStudentId(student_id : Int, onResult: (MeetingList?)->Unit) {
        apiHandler.getMeetingListByStudentId(student_id)?.enqueue(object : Callback<MeetingList?> {
            override fun onResponse(call: Call<MeetingList?>, response: Response<MeetingList?>) {
                if (response.code() == 200){
                    Log.d("CareerCarve", "onResponse: in if${response.body()}")
                    onResult(response.body())
                } else {
                    Log.d("CareerCarve", "onResponse: in else 1")
                    onResult(null)
                }
            }

            override fun onFailure(call: Call<MeetingList?>, t: Throwable) {
                onResult(null)
            }
        })
    }

}