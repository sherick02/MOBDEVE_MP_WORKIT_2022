package com.gonzaga.yu.workit.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


import com.gonzaga.yu.workit.data.model.WorkoutModel

import com.gonzaga.yu.workit.ui.base.BaseFragment
import com.gonzaga.yu.workit.utils.ParamArgus
import com.gonzaga.yu.workit.MainActivity
import com.gonzaga.yu.workit.R
import com.gonzaga.yu.workit.databinding.FragmentDoneExerciseBinding



class DoneExerciseFragment : BaseFragment(), View.OnClickListener {

    private val mParam1: String? = null
    private var binding: FragmentDoneExerciseBinding? = null
    private var workoutModel: WorkoutModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            workoutModel = requireArguments()!!.getSerializable(ParamArgus.MODEL) as WorkoutModel?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDoneExerciseBinding.inflate(inflater, container, false)
        val root = binding!!.root
        initViews()
        setListeners()
        return root
    }

    override fun initViews() {
        if (workoutModel != null) {
            binding!!.tvWorkoutName.text =
                workoutModel!!.workout_name + " Day " + workoutModel!!.day + " Completed!"
            binding!!.tvTotalDone.text =
                workoutModel!!.exerciseModels.size.toString() + " Exercise completed"
        }
    }

    override fun setListeners() {
        binding!!.tvDone.setOnClickListener(this)
    }

    override val actContext: Context
        protected get() = requireContext()
    override val fragmentContext: Fragment
        protected get() = this

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_done -> (requireActivity() as MainActivity).onDoBack()
        }
    }

    companion object {
        private const val ARG_PARAM1 = "param1"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment HomeFragment.
         */
        fun newInstance(param1: String?, param2: String?): DoneExerciseFragment {
            val fragment = DoneExerciseFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}