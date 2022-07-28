package com.sourabhverma.careercarveandroidapplication.entry_point.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.sourabhverma.careercarveandroidapplication.R
import com.sourabhverma.careercarveandroidapplication.databinding.ActivityEntryPointBinding
import com.sourabhverma.careercarveandroidapplication.entry_point.presentation.fragment.StudentOrMentorFragment
import com.sourabhverma.careercarveandroidapplication.entry_point.presentation.interfaces.ChangeFragment

class EntryPointActivity : AppCompatActivity() , ChangeFragment {
    private lateinit var binding : ActivityEntryPointBinding

    private lateinit var fragmentTransaction: FragmentTransaction

    private val fragment : StudentOrMentorFragment = StudentOrMentorFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntryPointBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpFragment()
    }

    private fun setUpFragment() {
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun replaceFragment(fragment: Fragment) {
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}