package com.gonzaga.yu.workit.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.View

import com.gonzaga.yu.workit.R
import com.gonzaga.yu.workit.databinding.ActivityLoginBinding
import com.gonzaga.yu.workit.MainActivity
import com.gonzaga.yu.workit.ui.base.BaseActivity

class LoginActivity : BaseActivity<ActivityLoginBinding?>(), View.OnClickListener {

    override val layoutId: Int get() = R.layout.activity_login

    override val context: Context protected get() = this

    override fun initViews(savedInstanceState: Bundle?) {}

    override fun setListeners() {
        binding!!.tvDontHaveAc.setOnClickListener(this)
        binding!!.mcvLogin.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_dont_have_ac -> startActivityWithAnim(RegisterActivity::class.java, false)
            R.id.mcv_login -> startActivityWithAnim(MainActivity::class.java, false)
        }
    }
}