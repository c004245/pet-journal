package com.example.pet_growth_journal.ui.total

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pet_growth_journal.databinding.FragTotalBinding

class TotalFragment : Fragment() {

    private var _binding: FragTotalBinding? = null
    private val binding get() = _binding!!

    private val totalViewModel: TotalViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragTotalBinding.inflate(inflater, container, false).apply {
            viewModel = totalViewModel
            lifecycleOwner = this@TotalFragment
        }
       return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}