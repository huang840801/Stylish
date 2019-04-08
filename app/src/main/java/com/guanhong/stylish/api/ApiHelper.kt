package com.guanhong.stylish.api

import android.util.Log
import com.google.gson.Gson
import com.guanhong.stylish.`object`.Data
import com.guanhong.stylish.`object`.Hots
import com.guanhong.stylish.`object`.Product
import com.guanhong.stylish.util.ApiConfig.Companion.MARKETING_HOTS_API
import okhttp3.*
import java.io.IOException

class ApiHelper {

    private val client = OkHttpClient()

    fun getMarketingHots() : Data?{
        val request = Request.Builder().url(MARKETING_HOTS_API).build()
        var hotsResponse : Data? = null
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response) {

                if (response.isSuccessful) {
    return hotsResponse

                }
                if (response!!.code() == 200) {
                    val responseBody = response.body()!!.string()
                    val gSon = Gson()
                    val getJason = gSon.fromJson<Data>(responseBody, Data::class.java)
                    hotsResponse = getJason

                    Log.d("Huang", hotsResponse!!.data[0].title)
                }
            }
            override fun onFailure(call: Call?, e: IOException?) {
                Log.d("Huang", " GetMarketingHots Fail")
            }
        })
        return hotsResponse
    }
}
