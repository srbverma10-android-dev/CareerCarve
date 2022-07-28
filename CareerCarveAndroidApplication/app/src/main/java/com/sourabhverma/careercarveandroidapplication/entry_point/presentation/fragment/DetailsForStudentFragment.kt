package com.sourabhverma.careercarveandroidapplication.entry_point.presentation.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sourabhverma.careercarveandroidapplication.R
import com.sourabhverma.careercarveandroidapplication.dashboard.presentation.activity.DashBoardActivity
import com.sourabhverma.careercarveandroidapplication.databinding.FragmentDetailsForStudentBinding
import com.sourabhverma.careercarveandroidapplication.entry_point.presentation.viewmodels.EntryPointViewModel

class DetailsForStudentFragment : Fragment() {

    private lateinit var binding: FragmentDetailsForStudentBinding

    private lateinit var viewModel: EntryPointViewModel

    private val myPREFERENCES = "MyPrefs"
    private val name = "nameKey"
    private val email = "emailKey"

    private var sharedpreferences: SharedPreferences? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(EntryPointViewModel::class.java)
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

        setOnClickListener()

        viewModelObserver()

    }

    private fun viewModelObserver() {

        viewModel.getAddStudentLiveData().observe(viewLifecycleOwner, {
            if(it?.data!= null){
                sharedpreferences = context?.getSharedPreferences(myPREFERENCES, Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = sharedpreferences!!.edit()
                editor.putString(name, binding.nameEditText.text.toString())
                editor.putString(email, binding.emailEditText.text.toString())
                editor.apply()
                val intent = Intent(requireContext(), DashBoardActivity::class.java)
                intent.putExtra("ShouldShowFAB", true)
                startActivity(intent)
            }
        })

    }

    private fun setOnClickListener() {

        binding.submitBtn.setOnClickListener {
            viewModel.addStudent(binding.nameEditText.text.toString(), binding.emailEditText.text.toString())
        }

    }

}