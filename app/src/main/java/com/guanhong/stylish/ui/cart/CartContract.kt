package com.guanhong.stylish.ui.cart

import android.content.Context
import com.guanhong.stylish.model.CartProduct

interface CartContract {
    interface View {
        fun showCartProductList(cartProducts: List<CartProduct>)
    }

    interface Presenter {
        fun getCartProductList(context: Context)
    }
}