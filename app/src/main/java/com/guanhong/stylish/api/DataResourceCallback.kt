package com.guanhong.stylish.api

import com.guanhong.stylish.`object`.Data

interface DataResourceCallback {

    interface GetMarketingHots{
        fun onSuccess(data: Data)
    }
}