package com.guanhong.stylish.ui.add2cart

import android.content.Context
import android.util.Log
import com.guanhong.stylish.api.DataResourceCallback
import com.guanhong.stylish.model.CartProduct
import com.guanhong.stylish.model.Product
import com.guanhong.stylish.model.Variant
import com.guanhong.stylish.repository.AddCartRepository
import javax.inject.Inject

class AddCartPresenter @Inject constructor(private val repository: AddCartRepository,
                                           private val view: AddCartFragment) : AddCartContract.Presenter {
    override fun addToCart(context: Context, cartProduct: CartProduct) {
        repository.addToCart(context, cartProduct , object : DataResourceCallback.AddToCart{
            override fun onSuccess() {
                view.showAddToCartResult(true)
            }
            override fun onFail() {
                view.showAddToCartResult(false)
            }
        })
    }
}