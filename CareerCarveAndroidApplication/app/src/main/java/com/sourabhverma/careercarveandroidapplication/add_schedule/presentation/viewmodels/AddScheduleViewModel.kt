package com.sourabhverma.careercarveandroidapplication.add_schedule.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sourabhverma.careercarveandroidapplication.add_schedule.data.remote.dto.AddMeeting
import com.sourabhverma.careercarveandroidapplication.add_schedule.data.remote.dto.ScheduleSuggestion
import com.sourabhverma.careercarveandroidapplication.add_schedule.data.remote.repository.AddScheduleRepo

class AddScheduleViewModel: ViewModel() {

    private val repo : AddScheduleRepo = AddScheduleRepo()
    private var suggestions : MutableLiveData<ScheduleSuggestion> = MutableLiveData()
    private var search : MutableLiveData<ScheduleSuggestion> = MutableLiveData()
    private var addMeeting : MutableLiveData<AddMeeting> = MutableLiveData()

    fun addMeeting(student_id : Int, mentor_id : Int, student_name : String, mentor_name : String,
        student_email: String, mentor_email: String, duration : Int, meeting_date : String, schedule_start : String, schedule_end : String){
        repo.addMeeting(student_id, mentor_id, student_name, mentor_name, student_email, mentor_email, duration, meeting_date, schedule_start, schedule_end){
            if (it != null && it.status && it.data != null){
                addMeeting.postValue(it)
            } else {
                addMeeting.postValue(null)
            }
        }
    }

    fun getAddMeetingLiveData() : MutableLiveData<AddMeeting> {
        return addMeeting
    }


    fun searchMentorById(id : Int){
        repo.searchMentorById(id){
            if (it != null && it.status && it.mentorDetails != null){
                search.postValue(it)
            } else {
                search.postValue(null)
            }
        }
    }

    fun getSearchMentorByIdLiveData() : MutableLiveData<ScheduleSuggestion> {
        return search
    }

    fun scheduleMeetingSuggestions(day : Int, area_of_intrest : Int){
        Log.d("CareerCarve", "scheduleMeetingSuggestions: $day , $area_of_intrest")
        repo.scheduleMeetingSuggestions(day, area_of_intrest){
            if (it != null && it.status && it.mentorDetails != null){
                suggestions.postValue(it)
            } else {
                suggestions.postValue(null)
            }
        }
    }

    fun getSuggestionsLiveData() : MutableLiveData<ScheduleSuggestion> {
        return suggestions
    }

}