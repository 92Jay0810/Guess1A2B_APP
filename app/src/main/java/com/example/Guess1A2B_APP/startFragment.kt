package com.example.Guess1A2B_APP

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.Guess1A2B_APP.R
import com.example.Guess1A2B_APP.databinding.FragmentStartBinding


class startFragment : Fragment() {
    //先建立binding
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       //
        binding.buttonStart.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_guessFragment)
        }
        binding.buttonHistory.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_historyFrament)
        }
        //記得在nav_graph 加入action
    }

}