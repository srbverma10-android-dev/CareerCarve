package com.sourabhverma.careercarveandroidapplication.dashboard.presentation.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sourabhverma.careercarveandroidapplication.add_schedule.presentation.activity.AddScheduleActivity
import com.sourabhverma.careercarveandroidapplication.add_schedule.presentation.viewmodels.AddScheduleViewModel
import com.sourabhverma.careercarveandroidapplication.dashboard.presentation.adapters.MeetingListAdapter
import com.sourabhverma.careercarveandroidapplication.dashboard.presentation.viewmodels.DashboardViewmodels
import com.sourabhverma.careercarveandroidapplication.databinding.ActivityDashBoardBinding
import com.sourabhverma.careercarveandroidapplication.utils.VARIABLES

class DashBoardActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDashBoardBinding

    private lateinit var viewModel: DashboardViewmodels

    private var adapterRV = MeetingListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(DashboardViewmodels::class.java)

        setContentView(binding.root)

        setFAB()

        setOnClickListener()

        viewModelObserver()

        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterRV
        }

    }

    private fun viewModelObserver() {
        viewModel.getMeetingListByStudentIdLiveData().observe(this, {
            if(it != null && it.data.isNotEmpty()){
                Log.d("SourabhKumarVerma", "viewModelObserver: " + it.data)
                adapterRV.setMeetingListData(it.data, this)
                adapterRV.notifyDataSetChanged()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        val prefs = getSharedPreferences(
            VARIABLES.MY_PREFERENCES, Context.MODE_PRIVATE
        )
        val type = prefs.getInt(VARIABLES.TYPE, -1)
        if (type == 1) {
            val student_id = prefs.getInt(VARIABLES.STUDENT_ID, -1)
            viewModel.getMeetingListByStudentId(student_id)
        }
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