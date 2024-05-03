package com.example.Guess1A2B_APP

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Guess1A2B_APP.adapter.historyGameAdapter
import com.example.Guess1A2B_APP.R
import com.example.Guess1A2B_APP.databinding.FragmentHistoryFramentBinding
import com.example.Guess1A2B_APP.model.gameViewModel


class historyFrament : Fragment() {
    //先建立binding
    private var _binding: FragmentHistoryFramentBinding? = null
    private val binding get() = _binding!!
    private val viewModel : gameViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryFramentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //修改
        binding.historyGameRecyclerview.adapter= historyGameAdapter(this.requireContext(),viewModel.gameResult)
        binding.historyGameRecyclerview.setHasFixedSize(true)
        binding.historyGameRecyclerview.layoutManager= LinearLayoutManager(this.requireContext())
        val adapter = binding.historyGameRecyclerview.adapter as historyGameAdapter
        adapter.setOnItemClickListener(object : historyGameAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                // 點擊事件處理
                viewModel.selectgame = position
                findNavController().navigate(R.id.action_historyFrament_to_stageHistoryFragment)
            }
        })
    }

}