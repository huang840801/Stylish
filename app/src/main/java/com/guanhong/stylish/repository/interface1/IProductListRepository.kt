package com.guanhong.stylish.repository.interface1

import com.guanhong.stylish.api.DataResourceCallback

interface IProductListRepository {
    fun getProductList(params: String, paging: String?, productListCallback: DataResourceCallback.GetProductList)
}