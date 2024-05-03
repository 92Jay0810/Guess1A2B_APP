package com.example.Guess1A2B_APP

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Guess1A2B_APP.adapter.wordAdapter
import com.example.Guess1A2B_APP.databinding.FragmentRecordHistoryBinding
import com.example.Guess1A2B_APP.model.gameViewModel

class recordHistoryFragment : Fragment() {
    private var _binding: FragmentRecordHistoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel : gameViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecordHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var selectgamenumber=viewModel.selectstage
        binding.historyRecordRecyclerview.adapter= wordAdapter(this.requireContext(),viewModel.gameResult.get(viewModel.selectgame).gameStageResult.get(selectgamenumber).guessResult,true)
        binding.historyRecordRecyclerview.setHasFixedSize(true)
        binding.historyRecordRecyclerview.layoutManager= LinearLayoutManager(this.requireContext())

    }

}