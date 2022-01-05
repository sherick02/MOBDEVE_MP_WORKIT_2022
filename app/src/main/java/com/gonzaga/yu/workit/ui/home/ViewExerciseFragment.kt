package com.gonzaga.yu.workit.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.gonzaga.yu.workit.data.model.ExerciseModel

import com.gonzaga.yu.workit.ui.base.BaseFragment
import com.gonzaga.yu.workit.utils.ParamArgus
import com.gonzaga.yu.workit.MainActivity
import com.gonzaga.yu.workit.R
import com.gonzaga.yu.workit.databinding.FragmentViewExerciseBinding



class ViewExerciseFragment : BaseFragment(), View.OnClickListener {
    private val mParam1: String? = null
    private var binding: FragmentViewExerciseBinding? = null
    private var exerciseModel: ExerciseModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            exerciseModel = requireArguments()!!.getSerializable(ParamArgus.MODEL) as ExerciseModel?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewExerciseBinding.inflate(inflater, container, false)
        val root = binding!!.root
        initViews()
        setListeners()
        return root
    }

    override fun initViews() {
        if (exerciseModel != null) {
            binding!!.tvExerciseName.text = exerciseModel!!.title
            binding!!.tvExerciseTitle.text = exerciseModel!!.title
            binding!!.tvReps.text =
                StringBuilder().append(exerciseModel!!.reps).append(" reps").toString()
            binding!!.tvSets.text = StringBuilder().append(exerciseModel!!.sets).append(" sets")
            binding!!.tvTime.text =
                StringBuilder().append(exerciseModel!!.rest_time).append(" seconds")
            binding!!.tvExerciseDes.text = exerciseModel!!.des
        }
    }

    override fun setListeners() {
        binding!!.tvBack.setOnClickListener(this)
    }

    override val actContext: Context
        protected get() = requireContext()
    override val fragmentContext: Fragment
        protected get() = this

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_back -> (requireActivity() as MainActivity).onDoBack()
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
        fun newInstance(param1: String?, param2: String?): ViewExerciseFragment {
            val fragment = ViewExerciseFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}