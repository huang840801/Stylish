package com.guanhong.stylish.repository.interface1

import android.content.Context
import com.guanhong.stylish.api.DataResourceCallback

interface ICartRepository {
    fun getCartProductList(context: Context,
                           getCartProductListCallback: DataResourceCallback.GetCartProductList)
}