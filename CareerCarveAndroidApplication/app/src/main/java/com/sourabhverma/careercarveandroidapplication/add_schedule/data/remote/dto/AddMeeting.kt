package com.sourabhverma.careercarveandroidapplication.add_schedule.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AddMeeting(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("data")
    val data: Int?
)
