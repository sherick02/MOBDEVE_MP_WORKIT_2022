package com.R.R.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.gonzaga.yu.workit.R
import com.R.R.data.common.Constants
import com.R.R.data.listeners.OnItemClickListener
import com.R.R.data.model.RepeatModel
import java.util.*

class RepeatAdapter(
    projects: List<RepeatModel>?,
    context: Context,
    onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<RepeatAdapter.SimpleViewHolder>() {

    private var homeModels: List<RepeatModel> = ArrayList()
    private val mContext: Context
    private val onItemClickListener: OnItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_repeat, parent, false)
        return SimpleViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.bindData(homeModels[position], position)
    }

    override fun getItemCount(): Int {
        return homeModels.size
    }

    inner class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mcv_repeat: MaterialCardView
        private val tv_day: TextView
        private val iv_selected: AppCompatImageView
        fun bindData(repeatModel: RepeatModel, position: Int) {
            try {
                tv_day.text = repeatModel.day
                if (repeatModel.isSelected) {
                    iv_selected.visibility = View.VISIBLE
                } else {
                    iv_selected.visibility = View.INVISIBLE
                }
                mcv_repeat.setOnClickListener { view: View? ->
                    homeModels[position].isSelected = !repeatModel.isSelected
                    if (repeatModel.isSelected) {
                        iv_selected.visibility = View.VISIBLE
                    } else {
                        iv_selected.visibility = View.INVISIBLE
                    }
                    Constants.days_selected.clear()
                    for (i in homeModels.indices) {
                        if (homeModels[i].isSelected) {
                            Constants.days_selected.add(i)
                        }
                    }
                    onItemClickListener.onItemClick(view, adapterPosition)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        init {
            mcv_repeat = itemView.findViewById(R.id.mcv_repeat)
            tv_day = itemView.findViewById(R.id.tv_day)
            iv_selected = itemView.findViewById(R.id.iv_selected)
        }
    }

    companion object {
        private val TAG = RepeatAdapter::class.java.name
    }

    init {
        if (projects != null) {
            homeModels = projects
        }
        mContext = context
        this.onItemClickListener = onItemClickListener
    }
}