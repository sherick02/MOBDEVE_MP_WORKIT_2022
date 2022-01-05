package com.gonzaga.yu.workit.ui.auth

import android.content.Context
import android.os.Bundle
import com.gonzaga.yu.workit.R
import com.gonzaga.yu.workit.databinding.ActivityRegisterBinding
import com.gonzaga.yu.workit.ui.base.BaseActivity

class RegisterActivity : BaseActivity<ActivityRegisterBinding?>() {

    override val layoutId: Int
        get() = R.layout.activity_register

    override val context: Context
        protected get() = this

    override fun initViews(savedInstanceState: Bundle?) {}

    override fun setListeners() {}
}