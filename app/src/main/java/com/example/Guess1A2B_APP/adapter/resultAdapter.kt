package com.example.Guess1A2B_APP.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.Guess1A2B_APP.R
import com.example.Guess1A2B_APP.model.stageResult

class resultAdapter (
    private val context: Context,
    private var dataset: MutableList<stageResult>,
)
    : RecyclerView.Adapter<resultAdapter.resultViewHolder>() {
    private var itemClickListener: OnItemClickListener? = null
    //點擊方法
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }
    inner class resultViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val stage: TextView =view.findViewById(R.id.stage)
        val answer: TextView =view.findViewById(R.id.answer)
        val tries: TextView =view.findViewById(R.id.tries_text)
        init {
            itemView.setOnClickListener {
                itemClickListener?.onItemClick(adapterPosition)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): resultViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_result, parent, false)
        return resultViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: resultViewHolder, position: Int) {
        val item = dataset[position]
        holder.stage.text = context.getString(R.string.stage, (position+1))
        holder.answer.text= context.getString(R.string.answer,item.answer)
        holder.tries.text=  context.getString(R.string.attempt,item.attempt)
    }

    override fun getItemCount() = dataset.size

}