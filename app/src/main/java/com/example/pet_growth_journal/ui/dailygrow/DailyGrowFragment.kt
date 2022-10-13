package com.example.pet_growth_journal.ui.dailygrow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.pet_growth_journal.databinding.FragDailyGrowBinding

class DailyGrowFragment : Fragment() {
    private var _binding: FragDailyGrowBinding? = null
    private val binding get() = _binding!!

    private val dailyGrowViewModel: DailyGrowViewModel by viewModels()

    private val dailyGrowAdapter by lazy {
        DailyGrowAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragDailyGrowBinding.inflate(inflater, container, false).apply {
            viewModel = dailyGrowViewModel
            lifecycleOwner = this@DailyGrowFragment
        }

        initViewPager()

        dailyGrowViewModel.dailyGrowModel.observe(viewLifecycleOwner, Observer {  dailyGrowModel ->
            dailyGrowAdapter.submitList(dailyGrowModel)
        })
        return binding.root
    }

    private fun initViewPager() {
        binding.vpGrowRecord.run {
            offscreenPageLimit = 1
            addItemDecoration(object : RecyclerView.ItemDecoration() {

            })
            setPageTransformer { page, position ->

            }
            adapter = dailyGrowAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}