package com.guanhong.stylish.repository

import android.content.Context
import com.guanhong.stylish.api.DataResourceCallback
import com.guanhong.stylish.repository.interface1.ICartRepository
import com.guanhong.stylish.sql.SqlDbHelper

class CartRepository : ICartRepository {

    override fun deleteCartProduct(context: Context,
                                   productId: String,
                                   deleteCartProductCallback: DataResourceCallback.DeleteCartProduct) {
        val sql = SqlDbHelper(context)

        if (sql.delete(productId) > 0) {

            deleteCartProductCallback.onSuccess()
        }
    }

    override fun getCartProductList(context: Context,
                                    getCartProductListCallback: DataResourceCallback.GetCartProductList) {
        val sql = SqlDbHelper(context)

        getCartProductListCallback.onSuccess(sql.getCartProduct(context))
    }
}