package com.sourabhverma.careercarveandroidapplication.dashboard.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sourabhverma.careercarveandroidapplication.add_schedule.presentation.activity.AddScheduleActivity
import com.sourabhverma.careercarveandroidapplication.databinding.ActivityDashBoardBinding

class DashBoardActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDashBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFAB()

        setOnClickListener()

    }

    private fun setFAB() {
        if(!intent.getBooleanExtra("ShouldShowFAB", false)){
            binding.addSchedule.visibility = View.GONE
        } else {
            binding.addSchedule.visibility = View.VISIBLE
        }
    }

    private fun setOnClickListener() {

        binding.addSchedule.setOnClickListener{
            val intent = Intent(this, AddScheduleActivity::class.java)
            startActivity(intent)
        }

    }
}