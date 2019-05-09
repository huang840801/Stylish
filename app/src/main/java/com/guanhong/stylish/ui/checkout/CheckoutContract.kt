package com.guanhong.stylish.ui.checkout

import com.guanhong.stylish.model.OrderCheckout

interface CheckoutContract {
    interface View{
    }
    interface Presenter{
        fun orderCheckout(orderCheckout: OrderCheckout)
    }
}