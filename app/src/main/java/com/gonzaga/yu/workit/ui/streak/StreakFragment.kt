package com.gonzaga.yu.workit.ui.streak

import android.content.Context

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gonzaga.yu.workit.databinding.FragmentStreakBinding
import com.gonzaga.yu.workit.ui.base.BaseFragment


/**
 * A simple [Fragment] subclass.
 * Use the [StreakFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StreakFragment : BaseFragment() {

    private var mParam1: String? = null
    private var binding: FragmentStreakBinding? = null

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
        binding = FragmentStreakBinding.inflate(inflater, container, false)
        val root = binding!!.root
        initViews()
        setListeners()
        return root
    }

    override fun initViews() {}

    override fun setListeners() {}

    override val actContext: Context?
        get() = requireContext()

    override val fragmentContext: Fragment
        get() = this

    companion object {
        private const val ARG_PARAM1 = "param1"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment HomeFragment.
         */
        fun newInstance(param1: String?, param2: String?): StreakFragment {
            val fragment = StreakFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}