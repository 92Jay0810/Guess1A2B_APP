package com.example.Guess1A2B_APP.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.Guess1A2B_APP.R
import com.example.Guess1A2B_APP.model.guessResult

class wordAdapter (
    private val context: Context,
    private var dataset: MutableList<guessResult>,
    private var isHistory : Boolean,
    )
    :RecyclerView.Adapter<wordAdapter.wordViewHolder>() {
    inner class wordViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val playerWord: TextView? = view.findViewById(R.id.play_word)
        val resultWord: TextView? = view.findViewById(R.id.result_word)
        val record :TextView?= view.findViewById(R.id.history_record)
        val input :TextView?= view.findViewById(R.id.hisroty_input)
        val result :TextView?= view.findViewById(R.id.history_result)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): wordViewHolder {
        if(isHistory){
            val adapterLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_history_guess, parent, false)
            return  wordViewHolder(adapterLayout)
        }else{
            val adapterLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_word, parent, false)
            return  wordViewHolder(adapterLayout)
        }
    }

    override fun onBindViewHolder(holder: wordViewHolder, position: Int) {
        val item = dataset[position]
        if(isHistory){
            holder.record?.text=context.getString(R.string.record,position+1)
            holder.input?.text=context.getString(R.string.input,item.player_word)
            holder.result?.text=context.getString(R.string.result,item.result)
        }else{
            holder.playerWord?.text = item.player_word
            holder.resultWord?.text=  item.result
        }

    }

    override fun getItemCount() = dataset.size

}