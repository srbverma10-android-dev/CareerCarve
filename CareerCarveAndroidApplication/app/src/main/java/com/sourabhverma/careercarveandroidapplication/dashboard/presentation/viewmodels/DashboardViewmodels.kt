package com.sourabhverma.careercarveandroidapplication.dashboard.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sourabhverma.careercarveandroidapplication.dashboard.data.remote.dto.MeetingList
import com.sourabhverma.careercarveandroidapplication.dashboard.data.remote.repository.DashboardRepo

class DashboardViewmodels : ViewModel() {
    private val repo : DashboardRepo = DashboardRepo()

    private var getMeetingListByStudentIdLiveData : MutableLiveData<MeetingList> = MutableLiveData()

    fun getMeetingListByStudentId(student_id : Int){
        repo.getMeetingsByStudentId(student_id) {
            if (it != null && it.status && it.data != null){
                getMeetingListByStudentIdLiveData.postValue(it)
            }else {
                getMeetingListByStudentIdLiveData.postValue(null)
            }
        }
    }

    fun getMeetingListByStudentIdLiveData() : MutableLiveData<MeetingList> {
        return getMeetingListByStudentIdLiveData
    }

}