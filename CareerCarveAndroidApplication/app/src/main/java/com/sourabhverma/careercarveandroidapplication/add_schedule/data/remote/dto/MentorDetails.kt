package com.sourabhverma.careercarveandroidapplication.add_schedule.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MentorDetails(
    @SerializedName("mentor_id")
    val mentor_id: Int,
    @SerializedName("mentor_name")
    val mentor_name: String,
    @SerializedName("mentor_email")
    val mentor_email: String,
    @SerializedName("area_of_interst")
    val area_of_interst: String
)


