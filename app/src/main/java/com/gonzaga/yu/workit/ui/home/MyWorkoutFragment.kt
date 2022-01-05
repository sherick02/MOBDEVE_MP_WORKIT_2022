package com.gonzaga.yu.workit.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

import com.gonzaga.yu.workit.data.listeners.OnItemClickListener
import com.gonzaga.yu.workit.data.model.HomeModel

import com.gonzaga.yu.workit.ui.base.BaseFragment
import com.gonzaga.yu.workit.utils.ParamArgus
import com.gonzaga.yu.workit.MainActivity
import com.gonzaga.yu.workit.R
import com.gonzaga.yu.workit.data.common.Constants
import com.gonzaga.yu.workit.databinding.FragmentMyWorkoutBinding
import com.gonzaga.yu.workit.ui.adapters.WorkoutAdapter


class MyWorkoutFragment : BaseFragment(), View.OnClickListener {

    private val mParam1: String? = null
    private var binding: FragmentMyWorkoutBinding? = null
    private var homeModel: HomeModel? = null
    private var workoutAdapter: WorkoutAdapter? = null
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
        binding = FragmentMyWorkoutBinding.inflate(inflater, container, false)
        val root = binding!!.root
        initViews()
        setListeners()
        return root
    }

    override fun initViews() {
        if (homeModel != null) {
            binding!!.tvTitle.text = homeModel!!.title
            binding!!.tvDays.text = "" + homeModel!!.days + " Workout"
        }
        workoutAdapter = WorkoutAdapter(
            Constants.workoutModelArrayList,
            requireActivity(),
            object : OnItemClickListener {
                override fun onItemClick(view: View?, position: Int) {}
                override fun onItemLongClick(view: View?, position: Int) {}
            })
        binding!!.rvHomeDetails.adapter = workoutAdapter
    }

    override fun setListeners() {
        binding!!.ivBack.setOnClickListener(this)
        binding!!.tvCreate.setOnClickListener(this)
    }

    override val actContext: Context
        protected get() = requireContext()

    override val fragmentContext: Fragment
        protected get() = this

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_back -> (requireActivity() as MainActivity).onDoBack()
            R.id.tv_create -> Navigation.findNavController(v)
                .navigate(R.id.action_work_dest_create_dest)
        }
    }

    override fun onResume() {
        super.onResume()
        workoutAdapter!!.notifyDataSetChanged()
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
        fun newInstance(param1: String?, param2: String?): MyWorkoutFragment {
            val fragment = MyWorkoutFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}