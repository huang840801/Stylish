package com.guanhong.stylish.ui.checkout

import android.util.Log
import com.guanhong.stylish.model.OrderCheckout
import com.guanhong.stylish.repository.CheckoutRepository
import javax.inject.Inject

class CheckoutPresenter @Inject constructor(private val view: CheckoutContract.View,
                                            private val repository: CheckoutRepository)
    : CheckoutContract.Presenter {

    override fun orderCheckout(orderCheckout: OrderCheckout) {

        Log.d("Huang", " " + orderCheckout.prime)
        Log.d("Huang", " " + orderCheckout.order.shipping)
        Log.d("Huang", " " + orderCheckout.order.payment)
        Log.d("Huang", " " + orderCheckout.order.subtotal)
        Log.d("Huang", " " + orderCheckout.order.freight)
        Log.d("Huang", " " + orderCheckout.order.total)
        Log.d("Huang", " " + orderCheckout.productList[0].id)
        Log.d("Huang", " " + orderCheckout.productList[0].name)
        Log.d("Huang", " " + orderCheckout.productList[0].price)
        Log.d("Huang", " " + orderCheckout.productList[0].size)
        Log.d("Huang", " " + orderCheckout.productList[0].qty)
        Log.d("Huang", " " + orderCheckout.productList[0].orderColor.code)
        Log.d("Huang", " " + orderCheckout.productList[0].orderColor.name)

    }
}