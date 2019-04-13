package com.guanhong.stylish.api

import android.util.Log
import com.google.gson.Gson
import com.guanhong.stylish.`object`.Data
import com.guanhong.stylish.util.ApiConfig.Companion.MARKETING_HOTS_API
import okhttp3.*
import java.io.IOException

class ApiHelper(private val marketingHotsCallback: DataResourceCallback.GetMarketingHots) {

    private val client = OkHttpClient()

    fun getMarketingHots() {
        val request = Request.Builder().url(MARKETING_HOTS_API).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response) {

                if (response.code() == 200) {
                    val responseBody = response.body()!!.string()
                    val gSon = Gson()
                    val getJason = gSon.fromJson<Data>(responseBody, Data::class.java)
                    Log.d("Huang", " callback ")
                    marketingHotsCallback.onSuccess(getJason)
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                Log.d("Huang", " GetMarketingHots Fail")
            }
        })
    }
}
