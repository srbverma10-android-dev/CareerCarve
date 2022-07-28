package com.sourabhverma.careercarveandroidapplication.entry_point.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sourabhverma.careercarveandroidapplication.R
import com.sourabhverma.careercarveandroidapplication.databinding.FragmentStudentOrMentorBinding
import com.sourabhverma.careercarveandroidapplication.entry_point.presentation.interfaces.ChangeFragment

class StudentOrMentorFragment : Fragment() {

    private lateinit var binding: FragmentStudentOrMentorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_student_or_mentor, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListeners()

    }

    private fun setOnClickListeners() {
        binding.studentBtn.setOnClickListener {
            val fr: Fragment = DetailsForStudentFragment()
            val fc: ChangeFragment = activity as ChangeFragment
            fc.replaceFragment(fr)
        }

        binding.mentorBtn.setOnClickListener {

        }
    }

}