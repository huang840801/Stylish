package com.guanhong.stylish.ui.cart

import android.content.Context
import com.guanhong.stylish.model.CartProduct

interface CartContract {
    interface View {
        fun showCartProductList(cartProductList: List<CartProduct>)
        fun updateCartProductList()
        fun showProgressBar()
        fun hideProgressBar()
    }

    interface Presenter {
        fun getCartProductList(context: Context)
        fun deleteCartProduct(context: Context, productId: String)
    }
}