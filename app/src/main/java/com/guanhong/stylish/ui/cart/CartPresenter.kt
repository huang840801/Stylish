package com.guanhong.stylish.ui.cart

import android.content.Context
import com.guanhong.stylish.api.DataResourceCallback
import com.guanhong.stylish.model.CartProduct
import com.guanhong.stylish.repository.CartRepository
import javax.inject.Inject

class CartPresenter @Inject constructor(private val repository: CartRepository,
                                        private val view: CartFragment) : CartContract.Presenter {
    override fun deleteCartProduct(context: Context, productId: String) {
        repository.deleteCartProduct(context, productId, object : DataResourceCallback.DeleteCartProduct {
            override fun onSuccess() {
                view.updateCartProduct()
            }
        })
    }

    override fun getCartProductList(context: Context) {

        view.showProgressBar()

        repository.getCartProductList(context, object : DataResourceCallback.GetCartProductList {
            override fun onSuccess(cartProducts: List<CartProduct>) {

                view.showCartProductList(cartProducts)
                view.hideProgressBar()
            }
        })
    }
}