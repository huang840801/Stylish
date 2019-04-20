package com.guanhong.stylish.repository

import com.guanhong.stylish.api.ApiHelper
import com.guanhong.stylish.api.DataResourceCallback
import com.guanhong.stylish.repository.interface1.IProductListRepository

class ProductListRepository :IProductListRepository {
    override fun getProductList(params: String, productListCallback: DataResourceCallback.GetProductList) {
        val apiHelper = ApiHelper()
        apiHelper.getProductList(params, productListCallback)
    }
}