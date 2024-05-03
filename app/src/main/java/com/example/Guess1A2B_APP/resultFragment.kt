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
import com.example.Guess1A2B_APP.databinding.FragmentResultBinding
import com.example.Guess1A2B_APP.model.gameViewModel
import com.example.Guess1A2B_APP.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private var _binding: FragmentResultBinding? = null
private val binding get() = _binding!!

class resultFragment : Fragment() {
    private val viewModel : gameViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.home.setOnClickListener{
            findNavController().navigate(R.id.action_resultFragment_to_startFragment)
        }
        binding.buttonNextGame.setOnClickListener{
            findNavController().navigate(R.id.action_resultFragment_to_guessFragment)
        }
        binding.history.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_historyFrament)
        }
        binding.score.text=getString(R.string.score, viewModel.gameResult.last().score)
        binding.gameRecyclerview.adapter= resultAdapter(this.requireContext(),viewModel.gameResult.last().gameStageResult)
        binding.gameRecyclerview.adapter!!.notifyDataSetChanged()
        binding.gameRecyclerview.setHasFixedSize(true)
        binding.gameRecyclerview.layoutManager= LinearLayoutManager(this.requireContext())
    }

}