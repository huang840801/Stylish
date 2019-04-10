package com.guanhong.stylish.api

import com.guanhong.stylish.`object`.Hots

interface DataResourceCallback {

    interface GetMarketingHots{
        fun onSuccess(hotsList: List<Hots>)
    }
}