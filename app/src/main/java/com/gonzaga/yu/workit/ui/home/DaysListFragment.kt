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
import com.gonzaga.yu.workit.data.model.HomeModel
import com.gonzaga.yu.workit.data.model.WorkoutModel
import com.gonzaga.yu.workit.ui.adapters.DaysAdapter
import com.gonzaga.yu.workit.ui.base.BaseFragment
import com.gonzaga.yu.workit.utils.ParamArgus
import com.gonzaga.yu.workit.MainActivity
import com.gonzaga.yu.workit.R
import com.gonzaga.yu.workit.databinding.FragmentDaysListBinding

import java.util.*


class DaysListFragment : BaseFragment(), View.OnClickListener {

    private val mParam1: String? = null
    private var binding: FragmentDaysListBinding? = null
    private var homeModel: HomeModel? = null
    private val workoutModels = ArrayList<WorkoutModel>()
    private var daysAdapter: DaysAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            homeModel = requireArguments()!!.getSerializable(ParamArgus.MODEL) as HomeModel?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDaysListBinding.inflate(inflater, container, false)
        val root = binding!!.root
        initViews()
        setListeners()
        return root
    }

    override fun initViews() {
        workoutModels.clear()
        var data_count = 7
        if (homeModel != null) {
            binding!!.tvTitle.text = homeModel!!.title
            binding!!.tvDays.text = "" + homeModel!!.days + " days"
            binding!!.tvExerciseCount.text = "" + homeModel!!.exercise_count + " Exercise"
            data_count = homeModel!!.days
        }
        for (i in 0 until data_count) {
            val exerciseModels = ArrayList<ExerciseModel>()
            for (j in 0..2) {
                val exerciseModel = ExerciseModel()
                exerciseModel.day = i + 1
                exerciseModel.title = "Jumping Jacks " + (j + 1)
                exerciseModel.des =
                    "How to jump jacks. Start with your feet in the bottom of the shoe where the sky meets."
                exerciseModel.workout_name = ""
                exerciseModel.sets = 3
                exerciseModel.reps = 20
                exerciseModel.rest_time = 5 //in Seconds
                exerciseModels.add(exerciseModel)
            }
            val workoutModel = WorkoutModel(homeModel!!.title, i + 1, exerciseModels)
            workoutModels.add(workoutModel)
        }
        daysAdapter = DaysAdapter(workoutModels, requireActivity(), object : OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                val bundle = Bundle()
                bundle.putSerializable(ParamArgus.MODEL, workoutModels[position])
                Navigation.findNavController(view!!)
                    .navigate(R.id.action_days_dest_to_exercise_list, bundle)
            }

            override fun onItemLongClick(view: View?, position: Int) {}
        })
        binding!!.rvHomeDetails.adapter = daysAdapter
    }

    override fun setListeners() {
        binding!!.ivBack.setOnClickListener(this)
    }

    override val actContext: Context
        protected get() = requireContext()
    override val fragmentContext: Fragment
        protected get() = this

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_back -> (requireActivity() as MainActivity).onDoBack()
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
        fun newInstance(param1: String?, param2: String?): DaysListFragment {
            val fragment = DaysListFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}