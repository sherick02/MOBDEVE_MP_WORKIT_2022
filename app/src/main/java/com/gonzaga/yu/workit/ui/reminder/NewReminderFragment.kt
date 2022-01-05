package com.gonzaga.yu.workit.ui.reminder

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat


import com.gonzaga.yu.workit.utils.ParamArgus
import com.gonzaga.yu.workit.utils.PearlTextUtils.isBlank
import com.gonzaga.yu.workit.MainActivity
import com.gonzaga.yu.workit.R
import com.gonzaga.yu.workit.data.common.Constants
import com.gonzaga.yu.workit.databinding.FragmentNewReminderBinding
import com.gonzaga.yu.workit.ui.base.BaseFragment
import java.text.SimpleDateFormat
import java.util.*


class NewReminderFragment() : BaseFragment(), View.OnClickListener {

    private val mParam1: String? = null
    private var binding: FragmentNewReminderBinding? = null
    var df = SimpleDateFormat("hh:mm a")
    var days = arrayOf("Sun", "Mon", "Tues", "Wed", "Thurs", "Fri", "Sat")
    var is_edit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            is_edit = requireArguments()!!.getBoolean(ParamArgus.IS_EDIT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewReminderBinding.inflate(inflater, container, false)
        val root = binding!!.root
        initViews()
        setListeners()
        return root
    }

    override fun initViews() {
        if (is_edit) {
            binding!!.tvTitle.text = "Edit Reminder"
            binding!!.tvDone.text = "Save"
        }
    }

    override fun setListeners() {
        binding!!.tvCancel.setOnClickListener(this)
        binding!!.mcvTime.setOnClickListener(this)
        binding!!.mcvRepeat.setOnClickListener(this)
        binding!!.tvDone.setOnClickListener(this)
    }

    override val actContext: Context
        protected get() = requireContext()

    override val fragmentContext: Fragment
        protected get() = this

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_cancel -> (requireActivity() as MainActivity).onDoBack()
            R.id.tv_done -> {
                if (isBlank(binding!!.edtTitle.text.toString().trim { it <= ' ' })) {
                    showToastMsg("Please enter title")
                    return
                }
                (requireActivity() as MainActivity).onDoBack()
            }
            R.id.mcv_time -> {
                val calendar = Calendar.getInstance()
                val hour = calendar[Calendar.HOUR_OF_DAY]
                val minute1 = calendar[Calendar.MINUTE]
                val materialTimePicker = MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_12H)
                    .setHour(hour)
                    .setMinute(minute1)
                    .build()
                materialTimePicker.addOnPositiveButtonClickListener(View.OnClickListener {
                    onTimeSet(
                        materialTimePicker.hour,
                        materialTimePicker.minute
                    )
                })
                materialTimePicker.show(childFragmentManager, "fragment_tag")
            }
            R.id.mcv_repeat -> Navigation.findNavController(v)
                .navigate(R.id.action_new_reminder_dest_to_repeat)
        }
    }

    private fun onTimeSet(newHour: Int, newMinute: Int) {
        val cal = Calendar.getInstance()
        cal[Calendar.HOUR_OF_DAY] = newHour
        cal[Calendar.MINUTE] = newMinute
        cal.isLenient = false
        val format = df.format(cal.time)
        binding!!.tvTime.text = format
    }

    override fun onResume() {
        super.onResume()
        if (Constants.days_selected != null && Constants.days_selected.size > 0) {
            val strings_days = ArrayList<String>()
            strings_days.clear()
            for (i in Constants.days_selected.indices) {
                strings_days.add(days[Constants.days_selected[i]])
            }
            binding!!.tvRepeat.text = ""
            binding!!.tvRepeat.text = strings_days.toString().replace("[", "").replace("]", "")
            Constants.days_selected.clear()
        }
    }

    companion object {
        private val ARG_PARAM1 = "param1"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment HomeFragment.
         */
        fun newInstance(param1: String?, param2: String?): NewReminderFragment {
            val fragment = NewReminderFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}