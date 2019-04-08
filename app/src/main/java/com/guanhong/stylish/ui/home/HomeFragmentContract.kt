package com.guanhong.stylish.ui.home

import com.guanhong.stylish.`object`.Data
import com.guanhong.stylish.api.DataResourceCallback

interface HomeFragmentContract {
    interface View{
      fun  onBindMarketingHots(hotsResponse: Data)
    }
    interface Presenter{
        fun getMarketingHots()
    }
}