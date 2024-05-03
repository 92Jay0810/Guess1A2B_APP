package com.example.Guess1A2B_APP

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Guess1A2B_APP.adapter.resultAdapter
import com.example.Guess1A2B_APP.databinding.FragmentStageHistoryBinding
import com.example.Guess1A2B_APP.model.gameViewModel
import com.example.Guess1A2B_APP.R


class stageHistoryFragment : Fragment() {
    private var _binding: FragmentStageHistoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel : gameViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStageHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var selectgamenumber=viewModel.selectgame
        binding.historyStageRecyclerview.adapter= resultAdapter(this.requireContext(),viewModel.gameResult.get(selectgamenumber).gameStageResult)
        binding.historyStageRecyclerview.setHasFixedSize(true)
        binding.historyStageRecyclerview.layoutManager= LinearLayoutManager(this.requireContext())
        val adapter = binding.historyStageRecyclerview.adapter as resultAdapter
        adapter.setOnItemClickListener(object : resultAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                // 點擊事件處理
                viewModel.selectstage=position
                findNavController().navigate(R.id.action_stageHistoryFragment_to_recordHistoryFragment)
            }
        })

    }
}