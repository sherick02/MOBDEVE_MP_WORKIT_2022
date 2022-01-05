package com.gonzaga.yu.workit.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.gonzaga.yu.workit.R
import com.gonzaga.yu.workit.data.listeners.OnItemClickListener
import com.gonzaga.yu.workit.data.model.WorkoutModel
import java.util.*

class WorkoutAdapter(
    projects: List<WorkoutModel>?,
    context: Context,
    onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<WorkoutAdapter.SimpleViewHolder>() {

    private var homeModels: List<WorkoutModel> = ArrayList()
    private val mContext: Context
    private val onItemClickListener: OnItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_days, parent, false)
        return SimpleViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.bindData(homeModels[position], position)
    }

    override fun getItemCount(): Int {
        return homeModels.size
    }

    inner class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mcv_row_day: MaterialCardView
        private val cl_row_day: ConstraintLayout
        private val materialCardView: MaterialCardView
        private val iv_day: AppCompatImageView
        private val tv_days: TextView
        fun bindData(workoutModel: WorkoutModel, position: Int) {
            try {
                tv_days.text = "Day " + workoutModel.workout_name
                mcv_row_day.setOnClickListener { view: View? ->
                    onItemClickListener.onItemClick(
                        view,
                        adapterPosition
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        init {
            mcv_row_day = itemView.findViewById(R.id.mcv_row_day)
            cl_row_day = itemView.findViewById(R.id.cl_row_day)
            materialCardView = itemView.findViewById(R.id.materialCardView)
            iv_day = itemView.findViewById(R.id.iv_day)
            tv_days = itemView.findViewById(R.id.tv_days)
        }
    }

    companion object {
        private val TAG = WorkoutAdapter::class.java.name
    }

    init {
        if (projects != null) {
            homeModels = projects
        }
        mContext = context
        this.onItemClickListener = onItemClickListener
    }
}