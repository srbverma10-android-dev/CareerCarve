package com.sourabhverma.careercarveandroidapplication.entry_point.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AddMentor(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("data")
    val data: Int?
)


