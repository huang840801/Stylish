package com.guanhong.stylish.repository.interface1

import android.content.Context
import com.guanhong.stylish.api.DataResourceCallback
import com.guanhong.stylish.model.CartProduct
import com.guanhong.stylish.model.Product
import com.guanhong.stylish.model.Variant

interface IAddCartRepository {
    fun addToCart(context: Context,cartProduct: CartProduct, addToCartCallback: DataResourceCallback.AddToCart)
}