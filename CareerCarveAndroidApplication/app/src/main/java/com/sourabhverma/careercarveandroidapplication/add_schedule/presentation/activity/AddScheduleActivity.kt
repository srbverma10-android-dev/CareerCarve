package com.sourabhverma.careercarveandroidapplication.add_schedule.presentation.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.sourabhverma.careercarveandroidapplication.add_schedule.presentation.dialogs.DatePicker
import com.sourabhverma.careercarveandroidapplication.add_schedule.presentation.viewmodels.AddScheduleViewModel
import com.sourabhverma.careercarveandroidapplication.databinding.ActivityAddScheduleBinding
import com.sourabhverma.careercarveandroidapplication.entry_point.presentation.viewmodels.EntryPointViewModel
import java.text.DateFormat
import java.util.*

class AddScheduleActivity : AppCompatActivity() , DatePickerDialog.OnDateSetListener{
    private lateinit var binding : ActivityAddScheduleBinding

    private lateinit var viewModel: AddScheduleViewModel

    private var day : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddScheduleBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(AddScheduleViewModel::class.java)
        setContentView(binding.root)

        setOnClickListener()

        viewModelObserver()

    }

    private fun viewModelObserver() {

        viewModel.getSuggestionsLiveData().observe(this, {
            if(it?.mentorDetails != null){
                Log.d("SourabhKumarVerma", "viewModelObserver: $it")
            } else {
                Log.d("SourabhKumarVerma", "viewModelObserver: error")
            }
        })

    }

    private fun setOnClickListener() {

        binding.selectDate.setOnClickListener {
            val mDatePickerDialogFragment = DatePicker()
            mDatePickerDialogFragment.show(supportFragmentManager, "DATE PICK")
        }

    }

    override fun onDateSet(p0: android.widget.DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val mCalendar: Calendar = Calendar.getInstance()
        mCalendar.set(Calendar.YEAR, year)
        mCalendar.set(Calendar.MONTH, month)
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        val selectedDate: String =
            DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.time)
        setDay(selectedDate)
        binding.selectDate.text = selectedDate
        Snackbar.make(binding.root, "Please wait we are scheduling a meeting for you.", Snackbar.LENGTH_SHORT).show()

        viewModel.scheduleMeetingSuggestions(day, 12)

    }

    private fun setDay(selectedDate: String) {
        val list = selectedDate.split(",")
        when(list[0]) {
            "Sunday" -> day = 0
            "Monday" -> day = 1
            "Tuesday" -> day = 2
            "Wednesday" -> day = 3
            "Thursday" -> day = 4
            "Friday" -> day = 5
            "Saturday" -> day = 6
        }
    }
}