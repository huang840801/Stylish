package com.guanhong.stylish.ui.add2cart

import android.content.Context
import com.guanhong.stylish.model.CartProduct
import com.guanhong.stylish.model.Product
import com.guanhong.stylish.model.Variant

interface AddCartContract {
    interface View{
        fun showAddToCartResult(isSuccess: Boolean)
    }
    interface Presenter{

        fun addToCart(context: Context, cartProduct: CartProduct)
    }
}