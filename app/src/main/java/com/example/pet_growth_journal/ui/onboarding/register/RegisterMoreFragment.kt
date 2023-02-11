package com.example.pet_growth_journal.ui.onboarding.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.pet_growth_journal.R
import com.example.pet_growth_journal.databinding.FragRegisterMoreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterMoreFragment: Fragment() {

    private var _binding: FragRegisterMoreBinding? = null
    private val binding get() = _binding!!

    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragRegisterMoreBinding.inflate(inflater, container, false).apply {
            viewModel = registerViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        registerViewModel.genderType.observe(viewLifecycleOwner, Observer {
            if (binding.etType.text.toString().isNotEmpty() && binding.etBirth.text.toString().isNotEmpty() && registerViewModel.genderType.value != PetGender.NONE) {
                registerViewModel.setMoreState(true)
            } else {
                registerViewModel.setMoreState(false)
            }
        })

        binding.etType.addTextChangedListener {
            if (binding.etType.text.toString().isNotEmpty() && binding.etBirth.text.toString().isNotEmpty() && registerViewModel.genderType.value != PetGender.NONE) {
                registerViewModel.setMoreState(true)
            } else {
                registerViewModel.setMoreState(false)
            }
        }

        binding.etBirth.addTextChangedListener {
            if (binding.etType.text.toString().isNotEmpty() && binding.etBirth.text.toString().isNotEmpty() && registerViewModel.genderType.value != PetGender.NONE) {
                registerViewModel.setMoreState(true)
            } else {
                registerViewModel.setMoreState(false)
            }
        }


        binding.btnNext.setOnClickListener {
            if (registerViewModel.moreEnable.value == true) {
                Navigation.findNavController(binding.root).navigate(R.id.action_registerMoreFragment_to_registerPictureFragment)
            }
        }

        return binding.root
    }

}