package com.sourabhverma.careercarveandroidapplication.entry_point.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AddStudent(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("data")
    val data: Int?
)
