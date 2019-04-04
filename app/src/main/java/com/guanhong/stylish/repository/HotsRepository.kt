package com.guanhong.stylish.repository

import android.util.Log
import com.guanhong.stylish.repository.interface1.IHotsRepository

class HotsRepository : IHotsRepository {
    override fun getMarketingHots(): String {
        Log.d("Huang", "  HotsRepository")

        return "rrrrr"
    }
}