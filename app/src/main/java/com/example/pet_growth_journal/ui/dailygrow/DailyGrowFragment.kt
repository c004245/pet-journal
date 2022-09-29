package com.example.pet_growth_journal.ui.dailygrow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.pet_growth_journal.databinding.FragDailyGrowBinding

class DailyGrowFragment : Fragment() {
    private var _binding: FragDailyGrowBinding? = null
    private val binding get() = _binding!!

    private val dailyGrowViewModel: DailyGrowViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragDailyGrowBinding.inflate(inflater, container, false).apply {
            viewModel = dailyGrowViewModel
            lifecycleOwner = this@DailyGrowFragment
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}