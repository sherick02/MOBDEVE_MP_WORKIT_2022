package com.gonzaga.yu.workit.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.gonzaga.yu.workit.data.listeners.OnItemClickListener
import com.gonzaga.yu.workit.data.model.ExerciseModel
import com.gonzaga.yu.workit.data.model.WorkoutModel
import com.gonzaga.yu.workit.ui.base.BaseFragment
import com.gonzaga.yu.workit.utils.ParamArgus
import com.gonzaga.yu.workit.MainActivity
import com.gonzaga.yu.workit.R
import com.gonzaga.yu.workit.databinding.FragmentDaysExerciseListBinding
import com.gonzaga.yu.workit.ui.adapters.ExerciseAdapter
import java.util.*


class DaysExerciseListFragment : BaseFragment(), View.OnClickListener {

    private val mParam1: String? = null
    private var binding: FragmentDaysExerciseListBinding? = null
    private var workoutModel: WorkoutModel? = null
    private val exerciseModels = ArrayList<ExerciseModel>()
    private var exerciseAdapter: ExerciseAdapter? = null

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
        binding = FragmentDaysExerciseListBinding.inflate(inflater, container, false)
        val root = binding!!.root
        initViews()
        setListeners()
        return root
    }

    override fun initViews() {
        exerciseModels.clear()
        if (workoutModel != null) {
            binding!!.tvTitle.text = workoutModel!!.workout_name
            binding!!.tvDays.text = "Day " + workoutModel!!.day
            binding!!.tvExerciseCount.text = "" + workoutModel!!.exerciseModels.size + " Exercise"
            exerciseModels.addAll(workoutModel!!.exerciseModels)
        }
        exerciseAdapter =
            ExerciseAdapter(exerciseModels, requireActivity(), object : OnItemClickListener {
                override fun onItemClick(view: View?, position: Int) {
                    val bundle = Bundle()
                    bundle.putSerializable(ParamArgus.MODEL, exerciseModels[position])
                    Navigation.findNavController(view!!)
                        .navigate(R.id.action_days_dest_to_view_exercise, bundle)
                }

                override fun onItemLongClick(view: View?, position: Int) {}
            })
        binding!!.rvExerciseList.adapter = exerciseAdapter
    }

    override fun setListeners() {
        binding!!.ivBack.setOnClickListener(this)
        binding!!.tvStart.setOnClickListener(this)
    }

    override val actContext: Context
        protected get() = requireContext()
    override val fragmentContext: Fragment
        protected get() = this

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_back -> (requireActivity() as MainActivity).onDoBack()
            R.id.tv_start -> {
                val bundle = Bundle()
                bundle.putSerializable(ParamArgus.MODEL, workoutModel)
                Navigation.findNavController(v)
                    .navigate(R.id.action_days_dest_to_run_exercise, bundle)
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
        fun newInstance(param1: String?, param2: String?): DaysExerciseListFragment {
            val fragment = DaysExerciseListFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}