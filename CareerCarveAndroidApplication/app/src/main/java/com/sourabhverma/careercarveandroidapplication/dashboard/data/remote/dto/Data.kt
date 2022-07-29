package com.sourabhverma.careercarveandroidapplication.dashboard.data.remote.dto

data class Data(
    val duration: String,
    val meeting_date: String,
    val meeting_id: Int,
    val mentor_email: String,
    val mentor_id: Int,
    val mentor_name: String,
    val schedule_end: String,
    val schedule_start: String,
    val student_email: String,
    val student_id: Int,
    val student_name: String
)