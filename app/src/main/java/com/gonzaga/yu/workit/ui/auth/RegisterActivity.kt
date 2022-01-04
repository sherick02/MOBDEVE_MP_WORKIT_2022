package workitkotlin.mobdeve.s11.gonzaga.luis.workitkotlin.ui.auth

import android.content.Context
import com.R.R.ui.base.BaseActivity
import android.os.Bundle
import com.gonzaga.yu.workit.workitkotlin.R
import com.gonzaga.yu.workit.databinding.ActivityRegisterBinding

class RegisterActivity : BaseActivity<ActivityRegisterBinding?>() {

    override val layoutId: Int
        get() = R.layout.activity_register

    override val context: Context
        protected get() = this

    override fun initViews(savedInstanceState: Bundle?) {}

    override fun setListeners() {}
}