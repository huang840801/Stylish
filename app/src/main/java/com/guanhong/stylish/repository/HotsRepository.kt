package com.guanhong.stylish.repository

import android.util.Log
import com.guanhong.stylish.`object`.Data
import com.guanhong.stylish.api.ApiHelper
import com.guanhong.stylish.repository.interface1.IHotsRepository

class HotsRepository : IHotsRepository {

    override fun getMarketingHots(): Data? {
        val apiHelper = ApiHelper()

        Log.d("Huang", apiHelper.getMarketingHots()!!.data[0].title)
        return  apiHelper.getMarketingHots()
    }
}