package com.gonzaga.yu.workit.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.widget.Barrier
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.gonzaga.yu.workit.R
import com.gonzaga.yu.workit.data.listeners.OnItemClickListener
import com.gonzaga.yu.workit.data.model.ReminderModel
import java.util.*

class ReminderAdapter(
    projects: List<ReminderModel>?,
    context: Context,
    onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ReminderAdapter.SimpleViewHolder>() {

    private var homeModels: List<ReminderModel> = ArrayList()
    private val mContext: Context
    private val onItemClickListener: OnItemClickListener
    private var isEditMode = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_reminder, parent, false)
        return SimpleViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.bindData(homeModels[position], position)
    }

    override fun getItemCount(): Int {
        return homeModels.size
    }

    inner class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mcv_row_reminder: MaterialCardView
        private val cl_row_day: ConstraintLayout
        private val tv_title: TextView
        private val iv_remove: AppCompatImageView
        private val tv_time: TextView
        private val switch_on: SwitchCompat
        private val labelBarrier: Barrier
        private val tv_repeat: TextView
        fun bindData(reminderModel: ReminderModel?, position: Int) {
            try {
                tv_title.text = "Title $position"
                iv_remove.visibility = if (isEditMode) View.VISIBLE else View.INVISIBLE
                mcv_row_reminder.setOnClickListener { view: View? ->
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
            mcv_row_reminder = itemView.findViewById(R.id.mcv_row_reminder)
            cl_row_day = itemView.findViewById(R.id.cl_row_day)
            tv_title = itemView.findViewById(R.id.tv_title)
            iv_remove = itemView.findViewById(R.id.iv_remove)
            tv_time = itemView.findViewById(R.id.edt_time)
            switch_on = itemView.findViewById(R.id.switch_on)
            labelBarrier = itemView.findViewById(R.id.labelBarrier)
            tv_repeat = itemView.findViewById(R.id.tv_repeat)
        }
    }

    fun setEditMode(isEditMode: Boolean) {
        this.isEditMode = isEditMode
    }

    companion object {
        private val TAG = ReminderAdapter::class.java.name
    }

    init {
        if (projects != null) {
            homeModels = projects
        }
        mContext = context
        this.onItemClickListener = onItemClickListener
    }
}