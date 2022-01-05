package com.gonzaga.yu.workit.ui.base

import androidx.databinding.ViewDataBinding
import androidx.appcompat.app.AppCompatActivity
import android.app.ProgressDialog
import android.os.Bundle
import android.os.Build
import androidx.databinding.DataBindingUtil
import android.content.Intent
import android.widget.Toast
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.gonzaga.yu.workit.R
import java.lang.Exception


abstract class BaseActivity<B : ViewDataBinding?> : AppCompatActivity() {

    private val TAG = context.javaClass.simpleName
    var mProgressDialog: ProgressDialog? = null

    @JvmField
    protected var binding: B? = null
    abstract val layoutId: Int
    protected abstract val context: Context
    protected abstract fun initViews(savedInstanceState: Bundle?)
    protected abstract fun setListeners()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = resources.getColor(R.color.white, this.theme)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(R.color.white)
        }
        binding = DataBindingUtil.setContentView(this, layoutId)
        initViews(savedInstanceState)
        setListeners()
    }

    fun startActivity(aClass: Class<*>?, withFinish: Boolean) {
        startActivity(Intent(context, aClass))
        if (withFinish) finish()
    }

    fun startActivityWithAnim(aClass: Class<*>?, withFinish: Boolean) {
        startActivity(Intent(context, aClass))
        overridePendingTransition(0,0)
        if (withFinish) finish()
    }

    fun startActivityWithoutAnim(aClass: Class<*>?, withFinish: Boolean) {
        startActivity(Intent(context, aClass))
        overridePendingTransition(0, 0)
        if (withFinish) finish()
    }

    protected fun showProgressDialog() {
        try {
            if (mProgressDialog == null) mProgressDialog =
                ProgressDialog.show(context, "", "Please wait...", false, false)
            if (!mProgressDialog!!.isShowing) mProgressDialog!!.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    protected fun hideDialog() {
        try {
            if (mProgressDialog != null && mProgressDialog!!.isShowing) {
                mProgressDialog!!.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showToastMsg(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun hideKeyBoard(view: View, mActivity: Activity) {
            val imm = mActivity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        fun showKeyBoard(v: View?, mActivity: Activity) {
            val imm = mActivity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(v, 0)
        }
    }
}