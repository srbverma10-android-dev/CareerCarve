package com.sourabhverma.careercarveandroidapplication.entry_point.data.remote.repository

import android.util.Log
import com.sourabhverma.careercarveandroidapplication.api_helper.ApiHelper
import com.sourabhverma.careercarveandroidapplication.entry_point.data.remote.dto.AddMentor
import com.sourabhverma.careercarveandroidapplication.entry_point.data.remote.dto.AddStudent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EntryPointRepo {

    private val apiHandler: ApiHelper = ApiHelper()

    fun addStudent(student_name : String, student_email : String, onResult: (AddStudent?)->Unit) {
        apiHandler.addStudent(student_name, student_email)?.enqueue(object : Callback<AddStudent?> {
            override fun onResponse(call: Call<AddStudent?>, response: Response<AddStudent?>) {
                if (response.code() == 200){
                    Log.d("CareerCarve", "onResponse: in if${response.body()}")
                    onResult(response.body())
                } else {
                    Log.d("CareerCarve", "onResponse: in else 1")
                    onResult(null)
                }
            }

            override fun onFailure(call: Call<AddStudent?>, t: Throwable) {
                Log.d("CareerCarve", "onResponse: in else 2 ${t.message}")
                onResult(null)
            }
        })
    }

    fun addMentor(mentor_name: String, mentor_email: String, area_of_intrest: Int,
                          avail_mon: Boolean, avail_tue: Boolean, avail_wed: Boolean, avail_thrus : Boolean,
                          avail_fri : Boolean, avail_sat : Boolean, avail_sun: Boolean,
                          schedule_start: String, schedule_end: String,
                          onResult: (AddMentor?)->Unit){
        apiHandler.addMentor(mentor_name, mentor_email, area_of_intrest, booleanToInt(avail_mon), booleanToInt(avail_tue), booleanToInt(avail_wed), booleanToInt(avail_thrus),
            booleanToInt(avail_fri), booleanToInt(avail_sat), booleanToInt(avail_sun), schedule_start, schedule_end)?.enqueue(object :
            Callback<AddMentor?>{
            override fun onResponse(call: Call<AddMentor?>, response: Response<AddMentor?>) {
                if (response.code() == 200){
                    Log.d("CareerCarve", "onResponse: in if${response.body()}")
                    onResult(response.body())
                } else {
                    Log.d("CareerCarve", "onResponse: in else 1")
                    onResult(null)
                }
            }

            override fun onFailure(call: Call<AddMentor?>, t: Throwable) {
                Log.d("CareerCarve", "onResponse: in else 2 ${t.message}")
                onResult(null)
            }

        })
    }

    fun booleanToInt(b: Boolean): Int {
        return if (b) 1 else 0
    }

}