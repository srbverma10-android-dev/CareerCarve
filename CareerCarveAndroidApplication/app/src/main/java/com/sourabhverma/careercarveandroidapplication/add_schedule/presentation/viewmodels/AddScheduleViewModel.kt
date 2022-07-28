package com.sourabhverma.careercarveandroidapplication.add_schedule.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sourabhverma.careercarveandroidapplication.add_schedule.data.remote.dto.ScheduleSuggestion
import com.sourabhverma.careercarveandroidapplication.add_schedule.data.remote.repository.AddScheduleRepo

class AddScheduleViewModel: ViewModel() {

    private val repo : AddScheduleRepo = AddScheduleRepo()
    private var suggestions : MutableLiveData<ScheduleSuggestion> = MutableLiveData()

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