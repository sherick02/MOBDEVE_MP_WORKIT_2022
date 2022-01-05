package com.gonzaga.yu.workit.utils

import android.content.Context
import android.content.SharedPreferences
import com.gonzaga.yu.workit.MyApplication.Companion.appContext
import com.gonzaga.yu.workit.data.common.Constants
import com.gonzaga.yu.workit.data.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object Preferences {

    const val KEY_USER_DATA = "key_user_data"
    const val KEY_IS_LOGGED_IN = "key_is_logged_in"
    const val KEY_VERSION = "key_version"
    private fun get(): SharedPreferences {
        return appContext!!.getSharedPreferences(Constants.MY_PREFS, Context.MODE_PRIVATE)
    }

    @JvmStatic
    fun removePref(): Boolean {
        return get().edit().clear().commit()
    }

    fun setString(_key: String?, value: String?): Boolean {
        return get().edit().putString(_key, value).commit()
    }

    fun setDouble(_key: String?, value: Float?): Boolean {
        return get().edit().putFloat(_key, value!!).commit()
    }

    fun getDouble(_key: String?): Float {
        return get().getFloat(_key, 0.0f)
    }

    fun getString(_key: String?): String? {
        return get().getString(_key, null)
    }

    fun getInt(_key: String?): Int {
        return get().getInt(_key, 0)
    }

    fun setInt(_key: String?, value: Int): Boolean {
        return get().edit().putInt(_key, value).commit()
    }

    fun getBoolean(_key: String?): Boolean {
        return get().getBoolean(_key, false)
    }

    fun setBoolean(_key: String?, value: Boolean): Boolean {
        return get().edit().putBoolean(_key, value).commit()
    }

    fun remove(_key: String?) {
        get().edit().remove(_key).commit()
    }

    fun isContainKey(_key: String?): Boolean {
        return get().contains(_key)
    }

    var userData: User?
        get() {
            try {
                val gson = Gson()
                val type = object : TypeToken<User?>() {}.type
                return gson.fromJson(getString(KEY_USER_DATA), type)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }
        set(profile) {
            try {
                val gson = Gson()
                val json = gson.toJson(profile)
                setString(KEY_USER_DATA, json)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
}