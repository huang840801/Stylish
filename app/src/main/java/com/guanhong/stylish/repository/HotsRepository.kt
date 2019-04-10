package com.guanhong.stylish.repository

import com.guanhong.stylish.api.ApiHelper
import com.guanhong.stylish.api.DataResourceCallback
import com.guanhong.stylish.repository.interface1.IHotsRepository

class HotsRepository : IHotsRepository {

    override fun getMarketingHots(marketingHotsCallback: DataResourceCallback.GetMarketingHots) {
        val apiHelper = ApiHelper(marketingHotsCallback)

          apiHelper.getMarketingHots()
    }
}