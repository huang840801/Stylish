package com.guanhong.stylish.repository

import android.util.Log
import com.guanhong.stylish.`object`.Data
import com.guanhong.stylish.api.ApiHelper
import com.guanhong.stylish.api.DataResourceCallback
import com.guanhong.stylish.repository.interface1.IHotsRepository
import okhttp3.Response

class HotsRepository : IHotsRepository {

    override fun getMarketingHots(marketingHotsCallback: DataResourceCallback.getMarketingHots) {
        val apiHelper = ApiHelper(marketingHotsCallback)

          apiHelper.getMarketingHots()
    }
}