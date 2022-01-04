package com.R.R.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.gonzaga.yu.workit.R
import com.R.R.data.listeners.OnItemClickListener
import com.R.R.data.model.HomeModel
import java.util.*

class HomeAdapter(
    projects: List<HomeModel>?,
    context: Context,
    onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<HomeAdapter.SimpleViewHolder>() {

    private var homeModels: List<HomeModel> = ArrayList()
    private val mContext: Context
    private val onItemClickListener: OnItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_home, parent, false)
        return SimpleViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.bindData(homeModels[position], position)
    }

    override fun getItemCount(): Int {
        return homeModels.size
    }

    inner class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mcv_row_home: MaterialCardView
        private val tv_title: TextView
        private val tv_days: TextView
        private val tv_exercise_count: TextView
        private val cl_row_home: ConstraintLayout
        fun bindData(homeModel: HomeModel, position: Int) {
            try {
                tv_title.text = homeModel.title
                tv_exercise_count.text = "" + homeModel.exercise_count + " Exercise"
                if (position == 2) {
                    tv_days.text = "" + homeModel.days + " Workout"
                    cl_row_home.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            mContext,
                            R.drawable.card_back_green
                        )
                    )
                } else {
                    tv_days.text = "" + homeModel.days + " days"
                    cl_row_home.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            mContext,
                            R.drawable.card_back_blue
                        )
                    )
                }
                mcv_row_home.setOnClickListener { view: View? ->
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
            mcv_row_home = itemView.findViewById(R.id.mcv_row_home)
            tv_title = itemView.findViewById(R.id.tv_title)
            tv_days = itemView.findViewById(R.id.tv_days)
            tv_exercise_count = itemView.findViewById(R.id.tv_exercise_count)
            cl_row_home = itemView.findViewById(R.id.cl_row_home)
        }
    }

    companion object {
        private val TAG = HomeAdapter::class.java.name
    }

    init {
        if (projects != null) {
            homeModels = projects
        }
        mContext = context
        this.onItemClickListener = onItemClickListener
    }
}