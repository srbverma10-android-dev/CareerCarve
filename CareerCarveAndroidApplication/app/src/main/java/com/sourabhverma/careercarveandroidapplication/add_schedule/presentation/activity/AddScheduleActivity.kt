package com.sourabhverma.careercarveandroidapplication.add_schedule.presentation.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.sourabhverma.careercarveandroidapplication.R
import com.sourabhverma.careercarveandroidapplication.add_schedule.presentation.dialogs.DatePicker
import com.sourabhverma.careercarveandroidapplication.add_schedule.presentation.viewmodels.AddScheduleViewModel
import com.sourabhverma.careercarveandroidapplication.databinding.ActivityAddScheduleBinding
import java.text.DateFormat
import java.util.*


class AddScheduleActivity : AppCompatActivity() , DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener{
    private lateinit var binding : ActivityAddScheduleBinding

    private lateinit var viewModel: AddScheduleViewModel

    private var day : Int = -1

    private var selectedItemPosition = -1

    private var courses = arrayOf(
        "Select duration of the meeting", "60 mins", "45 mins",
        "30 mins")

    private var payments = arrayOf(
        -1, 4000, 3000, 2000
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddScheduleBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(AddScheduleViewModel::class.java)
        setContentView(binding.root)

        binding.durationSpinner.onItemSelectedListener = this
        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_spinner_item,
            courses
        )
        ad.setDropDownViewResource(
            android.R.layout
                .simple_spinner_dropdown_item)
        binding.durationSpinner.adapter = ad


        setOnClickListener()

        viewModelObserver()

    }

    private fun viewModelObserver() {

        viewModel.getSuggestionsLiveData().observe(this, {
            if(it?.mentorDetails != null){
                Log.d("SourabhKumarVerma", "viewModelObserver: $it")
                val tempString = "${it.mentorDetails.mentor_name} (${it.mentorDetails.mentor_id})"
                binding.nameMentor.text = tempString
                binding.emailMentor.text = it.mentorDetails.mentor_email

                Glide.with(this)
                    .load(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_face_24, this.theme))
                    .apply (RequestOptions.placeholderOf(R.drawable.ic_baseline_face_24))
                    .circleCrop()
                    .into(binding.profileImageOfMentor)

                binding.suggestedMentorDetails.visibility = View.VISIBLE
                binding.durationSpinner.visibility = View.VISIBLE

            } else {
                Log.d("SourabhKumarVerma", "viewModelObserver: error")
            }
        })

        viewModel.getSearchMentorByIdLiveData().observe(this, {
            if(it?.mentorDetails != null){
                Log.d("SourabhKumarVerma", "viewModelObserver: ${it.mentorDetails}")
                val tempString = "${it.mentorDetails.mentor_name} (${it.mentorDetails.mentor_id})"
                binding.nameMentor.text = tempString
                binding.emailMentor.text = it.mentorDetails.mentor_email

                Glide.with(this)
                    .load(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_face_24, this.theme))
                    .apply (RequestOptions.placeholderOf(R.drawable.ic_baseline_face_24))
                    .circleCrop()
                    .into(binding.profileImageOfMentor)

                binding.mentorIdEditTextLayout.visibility = View.GONE
                binding.changeMentorBtn.visibility = View.GONE
            }
        })

    }

    private fun setOnClickListener() {

        binding.selectDate.setOnClickListener {
            val mDatePickerDialogFragment = DatePicker()
            mDatePickerDialogFragment.show(supportFragmentManager, "DATE PICK")
        }

        binding.changeMentorTv.setOnClickListener {
            binding.mentorIdEditTextLayout.visibility = View.VISIBLE
            binding.changeMentorBtn.visibility = View.VISIBLE
        }

        binding.changeMentorBtn.setOnClickListener {
            viewModel.searchMentorById(binding.mentorIdEditText.text.toString().toInt())
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

        viewModel.scheduleMeetingSuggestions(day, binding.areaOfInterestEditText.text.toString().toInt())

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

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
        if (position > 0) {
            selectedItemPosition = position
            binding.paymentDetails.text = "${payments[position]} Rs only /-"
            binding.paymentDetails.visibility = View.VISIBLE
            binding.payBtn.visibility = View.VISIBLE
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }
}