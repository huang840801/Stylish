package com.guanhong.stylish.repository.interface1

import com.guanhong.stylish.`object`.Data
import com.guanhong.stylish.api.DataResourceCallback
import okhttp3.Response

interface IHotsRepository {
    fun getMarketingHots(marketingHotsCallback: DataResourceCallback.getMarketingHots)
}