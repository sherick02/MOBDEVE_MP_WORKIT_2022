package workitkotlin.mobdeve.s11.gonzaga.luis.workitkotlin.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import workitkotlin.mobdeve.s11.gonzaga.luis.workitkotlin.MainActivity
import com.R.R.ui.base.BaseFragment
import com.R.R.utils.Preferences.removePref
import com.gonzaga.yu.workit.R
import com.gonzaga.yu.workit.databinding.FragmentLogoutBinding

/**
 * A simple [Fragment] subclass.
 * Use the [LogoutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LogoutFragment : BaseFragment(), View.OnClickListener {
    private var mParam1: String? = null
    private var binding: FragmentLogoutBinding? = null

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
        binding = FragmentLogoutBinding.inflate(inflater, container, false)
        val root = binding!!.root
        initViews()
        setListeners()
        return root
    }

    override fun initViews() {}
    override fun setListeners() {
        binding!!.btnYes.setOnClickListener(this)
        binding!!.btnNo.setOnClickListener(this)
    }

    override val actContext: Context
        protected get() = requireContext()
    override val fragmentContext: Fragment
        protected get() = this

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_yes -> {
                removePref()
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                intent.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK or
                            Intent.FLAG_ACTIVITY_NEW_TASK
                )
                startActivity(intent)
                requireActivity().finish()
            }
            R.id.btn_no -> (requireActivity() as MainActivity).onDoBack()
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
        fun newInstance(param1: String?, param2: String?): LogoutFragment {
            val fragment = LogoutFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}