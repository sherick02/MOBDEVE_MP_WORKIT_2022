package com.gonzaga.yu.workit.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

import com.gonzaga.yu.workit.data.model.ExerciseModel
import com.gonzaga.yu.workit.data.model.WorkoutModel

import com.gonzaga.yu.workit.ui.base.BaseFragment
import com.gonzaga.yu.workit.utils.ParamArgus
import com.gonzaga.yu.workit.R
import com.gonzaga.yu.workit.databinding.FragmentRunningExerciseBinding

import java.util.*


class RunningExerciseFragment : BaseFragment(), View.OnClickListener {

    private val mParam1: String? = null
    private var binding: FragmentRunningExerciseBinding? = null
    private var workoutModel: WorkoutModel? = null
    private var index = 0
    var exerciseModels = ArrayList<ExerciseModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate: ")
        if (arguments != null) {
            workoutModel = requireArguments()!!.getSerializable(ParamArgus.MODEL) as WorkoutModel?
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.e(TAG, "onViewStateRestored: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRunningExerciseBinding.inflate(inflater, container, false)
        val root = binding!!.root
        initViews()
        setListeners()
        return root
    }

    override fun initViews() {
        exerciseModels.clear()
        if (workoutModel != null) {
            if (workoutModel!!.exerciseModels != null && workoutModel!!.exerciseModels.size > 0) {
                exerciseModels.addAll(workoutModel!!.exerciseModels)
                binding!!.tvExerciseTitle.text = exerciseModels[index].title
                binding!!.tvReps.text = StringBuilder(exerciseModels[index].reps).append(" reps")
                binding!!.tvSets.text = StringBuilder(exerciseModels[index].sets).append(" sets")
                index++
            }
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
            R.id.tv_done -> {
                Log.e(TAG, "onClick: $index")
                if (index < exerciseModels.size) {
                    val bundle = Bundle()
                    bundle.putSerializable(ParamArgus.MODEL, exerciseModels[index])
                    Navigation.findNavController(v)
                        .navigate(R.id.action_running_dest_to_rest_time, bundle)
                } else {
                    val bundle = Bundle()
                    bundle.putSerializable(ParamArgus.MODEL, workoutModel)
                    Navigation.findNavController(v)
                        .navigate(R.id.action_running_dest_to_done_exercise, bundle)
                }
            }
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
        fun newInstance(param1: String?, param2: String?): RunningExerciseFragment {
            val fragment = RunningExerciseFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}