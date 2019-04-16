package com.guanhong.stylish.api

import android.util.Log
import com.google.gson.Gson
import com.guanhong.stylish.model.response.FacebookResponse
import com.guanhong.stylish.model.response.HotResponse
import com.guanhong.stylish.util.ApiConfig.Companion.MARKETING_HOTS_API
import com.guanhong.stylish.util.ApiConfig.Companion.USER_SIGN_IN_API
import com.guanhong.stylish.util.UserManager
import okhttp3.*
import java.io.IOException

class ApiHelper {

    private val client = OkHttpClient()

    fun getMarketingHots(marketingHotsCallback: DataResourceCallback.GetMarketingHots) {

        val request = Request.Builder().url(MARKETING_HOTS_API).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response) {

                if (response.code() == 200) {
                    val responseBody = response.body()!!.string()
                    val gSon = Gson()
                    val getJason = gSon.fromJson<HotResponse>(responseBody, HotResponse::class.java)

                    marketingHotsCallback.onSuccess(getJason)
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                Log.d("Huang", " getMarketingHots Fail")
            }
        })
    }

    fun getUserSignIn(facebookToken: String, getUserSignIn: DataResourceCallback.GetUserSignIn) {

        val formBody = FormBody.Builder()
                .add("provider", "facebook")
                .add("access_token", facebookToken)
                .build()
        val request = Request
                .Builder()
                .url(USER_SIGN_IN_API)
                .addHeader("Content-Type", "application/json")
                .post(formBody)
                .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response) {

                if (response.code() == 200) {
                    val responseBody = response.body()!!.string()
                    val gSon = Gson()
                    val getJason = gSon.fromJson<FacebookResponse>(responseBody, FacebookResponse::class.java)

                    Log.d("Huang", "  name = " +getJason.data.user.name)
                    Log.d("Huang", "  email = " +getJason.data.user.email)
                    UserManager.saveStylishToken(getJason.data.accessToken)
                    UserManager.accessExpired = getJason.data.accessExpired
                    UserManager.user = getJason.data.user

                    getUserSignIn.onSuccess()
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                Log.d("Huang", " getUserSignIn Fail")
            }
        })

    }
}
