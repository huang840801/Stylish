package com.guanhong.stylish.repository

import android.content.Context
import com.guanhong.stylish.api.DataResourceCallback
import com.guanhong.stylish.model.CartProduct
import com.guanhong.stylish.repository.interface1.IAddCartRepository
import com.guanhong.stylish.sql.SqlDbHelper

class AddCartRepository : IAddCartRepository {
    override fun addToCart(context: Context, cartProduct: CartProduct, addToCartCallback: DataResourceCallback.AddToCart) {

        val sql = SqlDbHelper.getDatabase(context)

        if (sql.insert(cartProduct)) {

          addToCartCallback.onSuccess()
        } else {
            addToCartCallback.onFail()
        }
    }
}