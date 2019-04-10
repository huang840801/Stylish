package com.guanhong.stylish.ui.home

import com.guanhong.stylish.`object`.Hots

interface HomeFragmentContract {
    interface View{
      fun  onBindMarketingHots(hotsList: List<Hots>)
    }
    interface Presenter{
        fun getMarketingHots()
    }
}