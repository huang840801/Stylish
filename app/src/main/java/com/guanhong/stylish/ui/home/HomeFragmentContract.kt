package com.guanhong.stylish.ui.home

interface HomeFragmentContract {
    interface View{
      fun  onBindMarketingHots(hotList: ArrayList<Any>)
    }
    interface Presenter{
        fun getMarketingHots()
    }
}