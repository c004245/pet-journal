package com.example.pet_growth_journal.ui.onboarding.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.pet_growth_journal.R
import com.example.pet_growth_journal.databinding.FragRegisterNameBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterNameFragment: Fragment() {

    private var _binding: FragRegisterNameBinding? = null
    private val binding get() = _binding!!

    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragRegisterNameBinding.inflate(inflater, container, false).apply {
            viewModel = registerViewModel
            lifecycleOwner = viewLifecycleOwner
        }


        binding.etName.addTextChangedListener {
            registerViewModel.setNameState(it.toString())
        }

        binding.btnNext.setOnClickListener {
            if (registerViewModel.nameEnable.value == true) {
                Navigation.findNavController(binding.root).navigate(R.id.action_registerNameFragment_to_registerMoreFragment)
            }
        }
        binding.ivBack.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_registerNameFragment_to_loginFragment)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}