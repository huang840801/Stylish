package com.guanhong.stylish.util

import android.content.Context
import android.util.Log
import com.guanhong.stylish.Stylish
import com.guanhong.stylish.model.User

object UserManager {

    const val USER_DATA = "user_data"
    const val USER_TOKEN = "user_token"

    var accessExpired = 0
    var user = User("", "", "", "", "")

    fun saveStylishToken(token: String) {

        Log.d("Huang", "saveStylishToken  "+token)
        Stylish.context.getSharedPreferences(USER_DATA, Context.MODE_PRIVATE)
                .edit()
                .putString(USER_TOKEN, token)
                .apply()
    }
}