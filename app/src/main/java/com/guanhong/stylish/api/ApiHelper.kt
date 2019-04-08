package com.guanhong.stylish.api

import android.util.Log
import com.google.gson.Gson
import com.guanhong.stylish.`object`.Data
import com.guanhong.stylish.util.ApiConfig.Companion.MARKETING_HOTS_API
import okhttp3.*
import java.io.IOException

class ApiHelper(private val marketingHotsCallback: DataResourceCallback.getMarketingHots) {

    private val client = OkHttpClient()

    fun getMarketingHots() {
        val request = Request.Builder().url(MARKETING_HOTS_API).build()
//        var hotsResponse: Data? = null
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response) {

                if (response.code() == 200) {
                    val responseBody = response.body()!!.string()
                    val gSon = Gson()
                    val getJason = gSon.fromJson<Data>(responseBody, Data::class.java)
//                    hotsResponse = getJason
                    Log.d("Huang", " success : " + getJason.data[0].title)
                    marketingHotsCallback.onSuccess(getJason.data[0].title)
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                Log.d("Huang", " GetMarketingHots Fail")
            }
        })
//        return hotsResponse!!
    }
}
