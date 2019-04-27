package com.guanhong.stylish.api

import com.guanhong.stylish.model.CartProduct
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
    interface AddToCart{
        fun onSuccess()
        fun onFail()
    }
    interface GetCartProductList{
        fun onSuccess(cartProducts: List<CartProduct>)
    }
}