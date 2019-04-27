package com.guanhong.stylish.repository

import android.content.Context
import com.guanhong.stylish.api.DataResourceCallback
import com.guanhong.stylish.repository.interface1.ICartRepository
import com.guanhong.stylish.sql.SqlDbHelper

class CartRepository : ICartRepository {
    override fun getCartProductList(context: Context,
                                    getCartProductListCallback: DataResourceCallback.GetCartProductList) {

        val sql = SqlDbHelper(context)

        getCartProductListCallback.onSuccess(sql.getCartProduct(context))
    }
}