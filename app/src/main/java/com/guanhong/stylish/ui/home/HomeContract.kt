package com.guanhong.stylish.ui.home

interface HomeContract {
    interface View{
      fun  onBindMarketingHots(hotList: ArrayList<Any>)
    }
    interface Presenter{
        fun getMarketingHots()
    }
}