package com.guanhong.stylish.api

import com.guanhong.stylish.model.response.HotResponse
import com.guanhong.stylish.model.response.ProductListResponse

interface DataResourceCallback {

    interface GetMarketingHots{
        fun onSuccess(response: HotResponse)
    }
    interface GetUserSignIn{
        fun onSuccess()
    }
    interface GetProductList{
        fun onSuccess(response: ProductListResponse)
    }
}