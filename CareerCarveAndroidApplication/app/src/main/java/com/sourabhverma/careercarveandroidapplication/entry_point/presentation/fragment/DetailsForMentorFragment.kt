package com.sourabhverma.careercarveandroidapplication.entry_point.presentation.fragment

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sourabhverma.careercarveandroidapplication.R
import com.sourabhverma.careercarveandroidapplication.databinding.FragmentDetailsForMentorBinding
import com.sourabhverma.careercarveandroidapplication.entry_point.presentation.viewmodels.EntryPointViewModel

class DetailsForMentorFragment : Fragment() {

    private lateinit var binding: FragmentDetailsForMentorBinding

    private lateinit var viewModel: EntryPointViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(EntryPointViewModel::class.java)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_details_for_mentor, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext())
            .load(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_face_24, requireContext().theme))
            .apply (RequestOptions.placeholderOf(R.drawable.ic_baseline_face_24))
            .circleCrop()
            .into(binding.profileImage)

        setOnClickListener()

        viewModelObserver()
    }

    private fun viewModelObserver() {
        viewModel.getAddMentorLiveData().observe(viewLifecycleOwner, {
            if(it != null){
                Toast.makeText(requireContext(), "not null", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private val timePickerDialogListenerStart: TimePickerDialog.OnTimeSetListener =
        TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            val formattedTime = "$hourOfDay:$minute:00"
            binding.startTiming.text = formattedTime
        }

    private val timePickerDialogListenerEnd: TimePickerDialog.OnTimeSetListener =
        TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            val formattedTime = "$hourOfDay:$minute:00"
            binding.endTiming.text = formattedTime
        }

    private fun setOnClickListener() {
        binding.startTiming.setOnClickListener {
            val timePicker = TimePickerDialog(
                requireContext(),
                timePickerDialogListenerStart,
                12,
                10,
                false
            )
            timePicker.show()
        }

        binding.endTiming.setOnClickListener {
            val timePicker = TimePickerDialog(
                requireContext(),
                timePickerDialogListenerEnd,
                12,
                10,
                false
            )
            timePicker.show()
        }

        binding.submitBtn.setOnClickListener {
            viewModel.addMentor(binding.nameEditText.text.toString(),
                binding.emailEditText.text.toString(),
                binding.aoiEditText.text.toString().toInt(),
                binding.monday.isChecked,
                binding.tuesday.isChecked,
                binding.wednesday.isChecked,
                binding.thrusday.isChecked,
                binding.friday.isChecked,
                binding.saturday.isChecked,
                binding.sunday.isChecked,
                binding.startTiming.text.toString(),
                binding.endTiming.text.toString()
                )
        }

    }


}