package com.sourabhverma.careercarveandroidapplication.entry_point.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sourabhverma.careercarveandroidapplication.entry_point.data.remote.dto.AddMentor
import com.sourabhverma.careercarveandroidapplication.entry_point.data.remote.repository.EntryPointRepo

class EntryPointViewModel : ViewModel() {
    private val repo : EntryPointRepo = EntryPointRepo()
    private var addMentorLiveData : MutableLiveData<AddMentor> = MutableLiveData()

    fun addMentor(mentor_name: String, mentor_email: String, area_of_intrest: Int,
                  avail_mon: Boolean, avail_tue: Boolean, avail_wed: Boolean, avail_thrus : Boolean,
                  avail_fri : Boolean, avail_sat : Boolean, avail_sun: Boolean,
                  schedule_start: String, schedule_end: String){
        repo.addMentor(mentor_name, mentor_email, area_of_intrest, avail_mon, avail_tue, avail_wed, avail_thrus,
            avail_fri, avail_sat, avail_sun, schedule_start, schedule_end){
            if (it != null && it.status && it.data != null){
                addMentorLiveData.postValue(it)
            }else {
                addMentorLiveData.postValue(null)
            }
        }
    }

    fun getAddMentorLiveData() : MutableLiveData<AddMentor>{
        return addMentorLiveData
    }

}