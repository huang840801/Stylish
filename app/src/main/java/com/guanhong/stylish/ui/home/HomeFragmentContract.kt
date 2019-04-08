package com.guanhong.stylish.ui.home

import com.guanhong.stylish.`object`.Data

interface HomeFragmentContract {
    interface View{
      fun  onBindMarketingHots(hotsResponse: Data)
    }
    interface Presenter{
        fun getMarketingHots()
    }
}