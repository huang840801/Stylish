package com.guanhong.stylish.ui.home

interface HomeFragmentContract {
    interface View{
      fun  onBindMarketingHots(hotsList: ArrayList<Any>)
    }
    interface Presenter{
        fun getMarketingHots()
    }
}