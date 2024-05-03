package com.example.Guess1A2B_APP.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.Guess1A2B_APP.R
import com.example.Guess1A2B_APP.model.gameResult

class historyGameAdapter (
    private val context: Context,
    private var dataset: MutableList<gameResult>,
    )
    : RecyclerView.Adapter<historyGameAdapter.historyGameViewHolder>() {
    private var itemClickListener: OnItemClickListener? = null
    //點擊方法
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }
     inner class historyGameViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val game: TextView =view.findViewById(R.id.game_index)
        val score: TextView =view.findViewById(R.id.game_score)
        init {
            itemView.setOnClickListener {
                itemClickListener?.onItemClick(adapterPosition)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): historyGameViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_history_game, parent, false)

        return historyGameViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: historyGameViewHolder, position: Int) {
        val item = dataset[position]
        holder.game.text = context.getString(R.string.game, (position+1))
        holder.score.text= context.getString(R.string.score,item.score)
    }

    override fun getItemCount() = dataset.size

}