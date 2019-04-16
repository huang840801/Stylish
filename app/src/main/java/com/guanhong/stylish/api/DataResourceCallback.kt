package com.guanhong.stylish.api

import com.guanhong.stylish.model.response.HotResponse

interface DataResourceCallback {

    interface GetMarketingHots{
        fun onSuccess(hotResponse: HotResponse)
    }
    interface GetUserSignIn{
        fun onSuccess()
    }
}