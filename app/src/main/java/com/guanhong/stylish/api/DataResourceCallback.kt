package com.guanhong.stylish.api

interface DataResourceCallback {

    interface getMarketingHots{
        fun onSuccess(s:String)

        fun onFail()
    }
}