package com.catnip.loginapp.utils.preference

import android.content.Context
import android.content.SharedPreferences

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class UserPreference(context: Context) {
    private var preference: SharedPreferences = context.getSharedPreferences(NAME, MODE)

    companion object {
        private const val NAME = "LoginApp" //app name or else
        private const val MODE = Context.MODE_PRIVATE
        private val PREF_IS_USER_LOGGED_IN = Pair("IS_USER_LOGGED_IN", false)
        private val PREF_USER_NAME = Pair("USER_NAME", null)
    }
    var isUserLoggedIn: Boolean
        get() = preference.getBoolean(PREF_IS_USER_LOGGED_IN.first, PREF_IS_USER_LOGGED_IN.second)
        set(value) = preference.edit {
            it.putBoolean(PREF_IS_USER_LOGGED_IN.first, value)
        }

    var userName: String?
        get() = preference.getString(PREF_USER_NAME.first, PREF_USER_NAME.second)
        set(value) = preference.edit {
            it.putString(PREF_IS_USER_LOGGED_IN.first, value)
        }
}

private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
    val editor = edit()
    operation(editor)
    editor.apply()
}
