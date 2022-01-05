package com.gonzaga.yu.workit.ui.reminder

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation


import com.gonzaga.yu.workit.utils.ParamArgus
import com.gonzaga.yu.workit.R
import com.gonzaga.yu.workit.data.listeners.OnItemClickListener
import com.gonzaga.yu.workit.data.model.ReminderModel
import com.gonzaga.yu.workit.databinding.FragmentReminderBinding
import com.gonzaga.yu.workit.ui.adapters.ReminderAdapter
import com.gonzaga.yu.workit.ui.base.BaseFragment

import java.util.*


class ReminderFragment : BaseFragment() {

    private var mParam1: String? = null
    private var binding: FragmentReminderBinding? = null
    var reminderModels = ArrayList<ReminderModel>()
    var reminderAdapter: ReminderAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = requireArguments()!!.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReminderBinding.inflate(inflater, container, false)
        val root = binding!!.root
        initViews()
        setListeners()
        return root
    }

    override fun initViews() {
        reminderModels.clear()
        for (i in 0..2) {
            val reminderModel = ReminderModel()
            reminderModels.add(reminderModel)
        }
        reminderAdapter =
            ReminderAdapter(reminderModels, requireActivity(), object : OnItemClickListener {
                override fun onItemClick(view: View?, position: Int) {
                    val bundle = Bundle()
                    bundle.putBoolean(ParamArgus.IS_EDIT, true)
                    Navigation.findNavController(view!!)
                        .navigate(R.id.action_reminder_dest_to_new_reminder, bundle)
                }

                override fun onItemLongClick(view: View?, position: Int) {}
            })
        binding!!.rvReminders.adapter = reminderAdapter
    }

    override fun setListeners() {
        binding!!.tvNewReminder.setOnClickListener { v: View? ->
            Navigation.findNavController(
                v!!
            ).navigate(R.id.action_reminder_dest_to_new_reminder)
        }
        binding!!.tvEdit.setOnClickListener {
            if (binding!!.tvEdit.text == "Edit") {
                binding!!.tvEdit.text = "Done"
                reminderAdapter!!.setEditMode(true)
            } else {
                binding!!.tvEdit.text = "Edit"
                reminderAdapter!!.setEditMode(false)
            }
            reminderAdapter!!.notifyDataSetChanged()
        }
    }

    override val actContext: Context
        protected get() = requireContext()

    override val fragmentContext: Fragment
        protected get() = this

    companion object {
        private const val ARG_PARAM1 = "param1"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment HomeFragment.
         */
        fun newInstance(param1: String?, param2: String?): ReminderFragment {
            val fragment = ReminderFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}