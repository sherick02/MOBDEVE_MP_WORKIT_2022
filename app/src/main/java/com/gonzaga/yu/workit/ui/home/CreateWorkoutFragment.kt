package com.gonzaga.yu.workit.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.dialog.MaterialAlertDialogBuilder

import com.gonzaga.yu.workit.data.listeners.OnItemClickListener
import com.gonzaga.yu.workit.data.model.WorkoutModel

import com.gonzaga.yu.workit.ui.base.BaseFragment
import com.gonzaga.yu.workit.utils.PearlTextUtils.isBlank
import com.gonzaga.yu.workit.MainActivity
import com.gonzaga.yu.workit.R
import com.gonzaga.yu.workit.data.common.Constants
import com.gonzaga.yu.workit.databinding.FragmentCreateWorkoutBinding
import com.gonzaga.yu.workit.ui.adapters.ExerciseAdapter



class CreateWorkoutFragment() : BaseFragment(), View.OnClickListener {

    private val mParam1: String? = null
    private var binding: FragmentCreateWorkoutBinding? = null
    private var exerciseAdapter: ExerciseAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Constants.exerciseModelArrayList.clear()
        if (arguments != null) {
            //workoutModel = (WorkoutModel) getArguments().getSerializable(ParamArgus.MODEL);
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateWorkoutBinding.inflate(inflater, container, false)
        val root = binding!!.root
        initViews()
        setListeners()
        return root
    }

    override fun initViews() {
        exerciseAdapter = ExerciseAdapter(
            Constants.exerciseModelArrayList,
            requireActivity(),
            object : OnItemClickListener {
                override fun onItemClick(view: View?, position: Int) {
                    /*Bundle bundle = new Bundle();
               bundle.putSerializable(ParamArgus.MODEL, exerciseModels.get(position));
               Navigation.findNavController(view).navigate(R.id.action_days_dest_to_view_exercise, bundle);*/
                }

                override fun onItemLongClick(view: View?, position: Int) {
                }
            })
        binding!!.rvExerciseList.adapter = exerciseAdapter
    }

    override fun setListeners() {
        binding!!.tvDone.setOnClickListener(this)
        binding!!.tvAddExercise.setOnClickListener(this)
    }

    override val actContext: Context
        protected get() = requireContext()
    override val fragmentContext: Fragment
        protected get() = this

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_done -> {
                if (Constants.exerciseModelArrayList.size == 0) {
                    showToastMsg("add at least one exercise")
                    return
                }
                val materialAlertDialogBuilder = MaterialAlertDialogBuilder(requireActivity())
                val customAlertDialogView = LayoutInflater.from(requireActivity())
                    .inflate(R.layout.dialog_name, null, false)
                materialAlertDialogBuilder.setView(customAlertDialogView)
                val dialog = materialAlertDialogBuilder.show()
                val editText = customAlertDialogView.findViewById<EditText>(R.id.edt_name)
                val textViewOk = customAlertDialogView.findViewById<TextView>(R.id.tv_ok)
                val textViewCancel = customAlertDialogView.findViewById<TextView>(R.id.tv_cancel)
                textViewOk.setOnClickListener(View.OnClickListener {
                    if (!isBlank(editText.text.toString().trim { it <= ' ' })) {
                        dialog.dismiss()
                        val workoutModel = WorkoutModel(
                            editText.text.toString().trim { it <= ' ' },
                            1,
                            Constants.exerciseModelArrayList
                        )
                        Constants.workoutModelArrayList.add(workoutModel)
                        (requireActivity() as MainActivity).onDoBack()
                    } else {
                        showToastMsg("Please enter name")
                    }
                })
                textViewCancel.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(v: View) {
                        dialog.dismiss()
                    }
                })
            }
            R.id.tv_add_exercise -> Navigation.findNavController(v)
                .navigate(R.id.action_work_dest_create_exercise)
        }
    }

    override fun onResume() {
        super.onResume()
        exerciseAdapter!!.notifyDataSetChanged()
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
        fun newInstance(param1: String?, param2: String?): CreateWorkoutFragment {
            val fragment = CreateWorkoutFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}