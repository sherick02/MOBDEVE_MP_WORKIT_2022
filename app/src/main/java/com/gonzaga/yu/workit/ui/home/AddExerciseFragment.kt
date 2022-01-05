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
import com.gonzaga.yu.workit.utils.PearlTextUtils.isBlank
import com.gonzaga.yu.workit.MainActivity
import com.gonzaga.yu.workit.R
import com.gonzaga.yu.workit.data.common.Constants
import com.gonzaga.yu.workit.databinding.FragmentAddExerciseBinding



class AddExerciseFragment : BaseFragment(), View.OnClickListener {

    private val mParam1: String? = null
    private var binding: FragmentAddExerciseBinding? = null
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
        binding = FragmentAddExerciseBinding.inflate(inflater, container, false)
        val root = binding!!.root
        initViews()
        setListeners()
        return root
    }

    override fun initViews() {}
    override fun setListeners() {
        binding!!.tvAdd.setOnClickListener(this)
    }

    override val actContext: Context
        protected get() = requireContext()
    override val fragmentContext: Fragment
        protected get() = this

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_add -> if (checkValidation()) {
                val exerciseModel = ExerciseModel()
                exerciseModel.day = 1
                exerciseModel.title = binding!!.edtExerciseTitle.text.toString().trim { it <= ' ' }
                exerciseModel.des = binding!!.edtExerciseDes.text.toString().trim { it <= ' ' }
                exerciseModel.workout_name = ""
                exerciseModel.sets = binding!!.edtSets.text.toString().trim { it <= ' ' }.toInt()
                exerciseModel.reps = binding!!.edtReps.text.toString().trim { it <= ' ' }.toInt()
                exerciseModel.rest_time =
                    binding!!.edtTime.text.toString().trim { it <= ' ' }.toInt()
                Constants.exerciseModelArrayList.add(exerciseModel)
                (requireActivity() as MainActivity).onDoBack()
            }
        }
    }

    private fun checkValidation(): Boolean {
        if (isBlank(binding!!.edtReps.text.toString().trim { it <= ' ' })) {
            showToastMsg("Please enter reps")
            return false
        } else if (binding!!.edtReps.text.toString().trim { it <= ' ' }.toInt() <= 0) {
            showToastMsg("Please enter valid reps")
        } else if (isBlank(binding!!.edtSets.text.toString().trim { it <= ' ' })) {
            showToastMsg("Please enter sets")
            return false
        } else if (binding!!.edtSets.text.toString().trim { it <= ' ' }.toInt() <= 0) {
            showToastMsg("Please enter valid sets")
        } else if (isBlank(binding!!.edtTime.text.toString().trim { it <= ' ' })) {
            showToastMsg("Please enter rest time")
            return false
        } else if (binding!!.edtTime.text.toString().trim { it <= ' ' }.toInt() <= 0) {
            showToastMsg("Please enter valid rest time")
        } else if (isBlank(binding!!.edtExerciseTitle.text.toString().trim { it <= ' ' })) {
            showToastMsg("Please enter title")
            return false
        } else if (isBlank(binding!!.edtExerciseDes.text.toString().trim { it <= ' ' })) {
            showToastMsg("Please enter description")
            return false
        }
        return true
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
        fun newInstance(param1: String?, param2: String?): AddExerciseFragment {
            val fragment = AddExerciseFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}