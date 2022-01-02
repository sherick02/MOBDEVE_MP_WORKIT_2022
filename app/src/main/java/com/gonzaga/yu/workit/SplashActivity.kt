package com.gonzaga.yu.workit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.gonzaga.yu.workit.R
import com.gonzaga.yu.workit.databinding.ActivitySplashBinding
import mp_workit_2022.com.gonzaga.yu.workit.ui.auth.LoginActivity
import com.R.R.ui.base.BaseActivity
import com.R.R.utils.Preferences

class SplashActivity : BaseActivity<ActivitySplashBinding?>() {

    var handler = Handler()

    override val layoutId: Int
        get() = R.layout.activity_splash

    override val context: Context
        protected get() = this

    override fun initViews(savedInstanceState: Bundle?) {
        handler.postDelayed(runnable, SPLASH_TIME_OUT.toLong())
    }

    override fun setListeners() {}

    var runnable = Runnable {
        val intent = Intent()
        if (Preferences.getBoolean(Preferences.KEY_IS_LOGGED_IN) && Preferences.userData != null) {
            intent.setClass(this, MainActivity::class.java)
        } else {
            intent.setClass(this, LoginActivity::class.java)
        }
        startActivity(intent)
        overridePendingTransition(0,0)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        handler.removeCallbacks(runnable)
    }

    companion object {
        private const val SPLASH_TIME_OUT = 2000
    }
}