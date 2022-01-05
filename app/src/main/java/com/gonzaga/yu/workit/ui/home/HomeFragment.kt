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
import com.gonzaga.yu.workit.R
import com.gonzaga.yu.workit.data.common.Constants
import com.gonzaga.yu.workit.databinding.FragmentHomeBinding
import com.gonzaga.yu.workit.ui.adapters.HomeAdapter

import java.util.*


class HomeFragment : BaseFragment() {

    private var mParam1: String? = null
    private var binding: FragmentHomeBinding? = null
    private val homeModels = ArrayList<HomeModel>()
    private var homeAdapter: HomeAdapter? = null

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
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root = binding!!.root
        initViews()
        setListeners()
        return root
    }

    override fun initViews() {
        homeModels.clear()
        val homeModel = HomeModel(resources.getString(R.string.burst_workout), 7, 4)
        val homeModel1 = HomeModel(getString(R.string.busy_schedule_work), 3, 3)
        val homeModel2 =
            HomeModel(getString(R.string.my_own_work), Constants.workoutModelArrayList.size, 2)
        homeModels.add(homeModel)
        homeModels.add(homeModel1)
        homeModels.add(homeModel2)
        homeAdapter = HomeAdapter(homeModels, requireActivity(), object : OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                val bundle = Bundle()
                bundle.putSerializable(ParamArgus.MODEL, homeModels[position])
                when (position) {
                    0, 1 -> Navigation.findNavController(view!!)
                        .navigate(R.id.action_home_dest_to_home_details, bundle)
                    2 -> Navigation.findNavController(view!!)
                        .navigate(R.id.action_home_dest_to_my_work, bundle)
                }
            }

            override fun onItemLongClick(view: View?, position: Int) {}
        })
        binding!!.rvHome.adapter = homeAdapter
    }

    override fun setListeners() {}
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
        fun newInstance(param1: String?, param2: String?): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}