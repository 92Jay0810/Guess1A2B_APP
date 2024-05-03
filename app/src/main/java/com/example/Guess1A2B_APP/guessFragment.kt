package com.example.Guess1A2B_APP

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Guess1A2B_APP.adapter.wordAdapter
import com.example.Guess1A2B_APP.R
import com.example.Guess1A2B_APP.databinding.FragmentGuessBinding
import com.example.Guess1A2B_APP.model.guessResult
import com.example.Guess1A2B_APP.model.gameViewModel


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//先建立_binding (為了之後的view binding  每個fragment有自己的binding
//binding 為_binding的get 方法
private var _binding: FragmentGuessBinding? = null
private val binding get() = _binding!!

class guessFragment : Fragment() {
    //activityViewModel 從mainActivity 拿共用的viewModel
    private val viewModel : gameViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //修改成view binding
        _binding = FragmentGuessBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //在on View Created加入button事件
        binding.buttonGuess.setOnClickListener {
            onSubmitWord()
        }
        //記得在nav_graph 加入action
        //再去初始化 score 和attempts 以及回合數stage
        updateNextWordOnScreen()
        binding.wordRecyclerview.adapter=
            wordAdapter(this.requireContext(),viewModel.guessResult,false)
        binding.wordRecyclerview.setHasFixedSize(true)
        binding.wordRecyclerview.layoutManager= LinearLayoutManager(this.requireContext())

    }
    private fun onSubmitWord() {
        val playerWord = binding.textInputEditText.text.toString()
        if(playerWord.length==4){
            var result = viewModel.isUserWordCorrect(playerWord)
            var tempGuessResult= guessResult(playerWord,result)
            viewModel.guessResult.add(tempGuessResult)
            if(result=="4A0B"){
                binding.textInputEditText.text = null
                viewModel.nextStage()
                var context =requireContext()
                Toast.makeText(context,"correct!", Toast.LENGTH_SHORT).show()
            }
            if(viewModel.attempt==0){
                binding.textInputEditText.text = null
                viewModel.nextStage()
            }
            if(viewModel.stage==5){
                viewModel.reinitializeData()
                findNavController().navigate(R.id.action_guessFragment_to_resultFragment)
            }
            binding.wordRecyclerview.adapter!!.notifyDataSetChanged()
            updateNextWordOnScreen()
        }else{
            var context =requireContext()
            Toast.makeText(context,"input error!", Toast.LENGTH_SHORT).show()
        }
    }
    private fun updateNextWordOnScreen() {
        binding.stageCount.text=getString(R.string.stage_count, viewModel.stage ,4)
        binding.score.text = getString(R.string.score, viewModel.score)
        binding.attempts.text=getString(R.string.attempt,viewModel.attempt)
    }


}