package com.gonzaga.yu.workit.utils

import android.net.ConnectivityManager
import android.app.Activity
import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import android.content.DialogInterface
import com.gonzaga.yu.workit.R

object Utils {
    fun isNetworkAvailable(
        context: Context?,
        canShowErrorDialogOnFail: Boolean,
        isFinish: Boolean
    ): Boolean {
        var isNetAvailable = false
        if (context != null) {
            val mConnectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (mConnectivityManager != null) {
                var mobileNetwork = false
                var wifiNetwork = false
                var mobileNetworkConnecetd = false
                var wifiNetworkConnecetd = false
                val mobileInfo =
                    mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                val wifiInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                if (mobileInfo != null) {
                    mobileNetwork = mobileInfo.isAvailable
                }
                if (wifiInfo != null) {
                    wifiNetwork = wifiInfo.isAvailable
                }
                if (wifiNetwork || mobileNetwork) {
                    if (mobileInfo != null) mobileNetworkConnecetd = mobileInfo
                        .isConnectedOrConnecting
                    wifiNetworkConnecetd = wifiInfo!!.isConnectedOrConnecting
                }
                isNetAvailable = mobileNetworkConnecetd || wifiNetworkConnecetd
            }
            context.setTheme(R.style.Theme_WorkIt)
            if (!isNetAvailable && canShowErrorDialogOnFail) {
                if (context is Activity) {
                    context.runOnUiThread {
                        showAlertWithFinish(
                            context,
                            context.getString(R.string.no_internet),
                            context.getString(R.string.network_alert),
                            isFinish
                        )
                    }
                }
            }
        }
        return isNetAvailable
    }

    fun showAlertWithFinish(
        activity: Activity,
        title: String?,
        message: String?,
        isFinish: Boolean
    ) {
        MaterialAlertDialogBuilder(activity)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(activity.getString(R.string.ok)) { dialog: DialogInterface, which: Int ->
                if (isFinish) {
                    dialog.dismiss()
                    activity.finish()
                } else {
                    dialog.dismiss()
                }
            }.show()
    }
}