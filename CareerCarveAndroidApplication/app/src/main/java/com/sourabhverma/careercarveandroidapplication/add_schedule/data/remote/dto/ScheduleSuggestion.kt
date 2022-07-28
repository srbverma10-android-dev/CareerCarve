package com.sourabhverma.careercarveandroidapplication.add_schedule.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ScheduleSuggestion(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("mentorDetails")
    val mentorDetails: MentorDetails?
)

