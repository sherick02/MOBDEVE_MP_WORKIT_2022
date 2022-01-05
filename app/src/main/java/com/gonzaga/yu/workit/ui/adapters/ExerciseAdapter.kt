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
import com.gonzaga.yu.workit.data.model.ExerciseModel
import java.util.*

class ExerciseAdapter(
    projects: List<ExerciseModel>?,
    context: Context,
    onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ExerciseAdapter.SimpleViewHolder>() {

    private var homeModels: List<ExerciseModel> = ArrayList()
    private val mContext: Context
    private val onItemClickListener: OnItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_exercise, parent, false)
        return SimpleViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.bindData(homeModels[position], position)
    }

    override fun getItemCount(): Int {
        return homeModels.size
    }

    inner class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mcv_row_exercise: MaterialCardView
        private val cl_row_exercise: ConstraintLayout
        private val materialCardView: MaterialCardView
        private val iv_day: AppCompatImageView
        private val tv_exercise_title: TextView
        private val tv_exercise_des: TextView
        fun bindData(exerciseModel: ExerciseModel, position: Int) {
            try {
                tv_exercise_title.text = "" + exerciseModel.title
                tv_exercise_des.text =
                    "" + exerciseModel.sets + " sets x" + " " + exerciseModel.reps + " reps"
                mcv_row_exercise.setOnClickListener { view: View? ->
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
            tv_exercise_title = itemView.findViewById(R.id.edt_exercise_title)
            mcv_row_exercise = itemView.findViewById(R.id.mcv_row_exercise)
            cl_row_exercise = itemView.findViewById(R.id.cl_row_exercise)
            materialCardView = itemView.findViewById(R.id.materialCardView)
            iv_day = itemView.findViewById(R.id.iv_day)
            tv_exercise_des = itemView.findViewById(R.id.edt_exercise_des)
        }
    }

    companion object {
        private val TAG = ExerciseAdapter::class.java.name
    }

    init {
        if (projects != null) {
            homeModels = projects
        }
        mContext = context
        this.onItemClickListener = onItemClickListener
    }
}