package com.sourabhverma.careercarveandroidapplication.entry_point.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sourabhverma.careercarveandroidapplication.R
import com.sourabhverma.careercarveandroidapplication.databinding.FragmentDetailsForStudentBinding

class DetailsForStudentFragment : Fragment() {

    private lateinit var binding: FragmentDetailsForStudentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_details_for_student, container, false
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
    }

}