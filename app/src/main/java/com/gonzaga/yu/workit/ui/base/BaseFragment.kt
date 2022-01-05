package com.R.R.ui.base

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.gonzaga.yu.workit.R
import android.content.DialogInterface
import android.app.Activity
import android.app.Service
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import java.lang.Exception

abstract class BaseFragment : Fragment() {
    @JvmField
    val TAG = fragmentContext.javaClass.simpleName
    var mProgressDialog: ProgressDialog? = null
    abstract fun initViews()
    abstract fun setListeners()
    protected abstract val actContext: Context?
    protected abstract val fragmentContext: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setEnterTransition(new MaterialFadeThrough());
    }

    protected fun showProgressDialog() {
        try {
            if (mProgressDialog == null) mProgressDialog =
                ProgressDialog.show(actContext, "", "Please wait...", false, false)
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
        Toast.makeText(actContext, msg, Toast.LENGTH_SHORT).show()
    }

    fun showInfoMsgDlg(title: String?, msg: String?) {
        MaterialAlertDialogBuilder(actContext!!)
            .setTitle(title)
            .setMessage(msg)
            .setPositiveButton(getString(R.string.ok)) { dialogInterface: DialogInterface?, i: Int -> }
            .show()
    }

    companion object {
        fun hideKeyBoard(view: View, mActivity: Context) {
            val imm = mActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        fun showKeyBoard(v: View?, mActivity: Activity) {
            val imm = mActivity.getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(v, 0)
        }
    }
}