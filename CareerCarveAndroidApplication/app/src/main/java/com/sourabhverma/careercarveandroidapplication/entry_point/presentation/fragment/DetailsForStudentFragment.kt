package com.sourabhverma.careercarveandroidapplication.entry_point.presentation.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import com.google.android.material.snackbar.Snackbar
import com.sourabhverma.careercarveandroidapplication.R
import com.sourabhverma.careercarveandroidapplication.dashboard.presentation.activity.DashBoardActivity
import com.sourabhverma.careercarveandroidapplication.databinding.FragmentDetailsForStudentBinding
import com.sourabhverma.careercarveandroidapplication.entry_point.presentation.viewmodels.EntryPointViewModel
import com.sourabhverma.careercarveandroidapplication.utils.VARIABLES

class DetailsForStudentFragment : Fragment() {

    private lateinit var binding: FragmentDetailsForStudentBinding

    private lateinit var viewModel: EntryPointViewModel

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
                sharedpreferences = context?.getSharedPreferences(VARIABLES.MY_PREFERENCES, Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = sharedpreferences!!.edit()
                editor.putString(VARIABLES.NAME, binding.nameEditText.text.toString())
                editor.putString(VARIABLES.EMAIL, binding.emailEditText.text.toString())
                editor.putInt(VARIABLES.STUDENT_ID, it.data)
                editor.putInt(VARIABLES.TYPE, 1)
                editor.apply()
                val intent = Intent(requireContext(), DashBoardActivity::class.java)
                intent.putExtra("ShouldShowFAB", true)
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun setOnClickListener() {

        binding.submitBtn.setOnClickListener {
            if (binding.nameEditText.text.toString().isNotEmpty() &&
                binding.emailEditText.text.toString().isNotEmpty()) {
                viewModel.addStudent(binding.nameEditText.text.toString(), binding.emailEditText.text.toString())
            } else {
               Snackbar.make(binding.root, "Fill all fields...", Snackbar.LENGTH_SHORT).show()
            }
        }

    }

}