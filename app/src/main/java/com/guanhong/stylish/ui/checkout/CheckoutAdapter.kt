package com.guanhong.stylish.ui.checkout

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.guanhong.stylish.R
import com.guanhong.stylish.model.CartProduct

class CheckoutAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var cartProductList: List<CartProduct> = listOf()
    private var productAmountTotal = 0
    private var productNumber = 0

    companion object {
        private const val CART_PRODUCT_TYPE = 1
        private const val USER_INFO_TYPE = 2

    }

    override fun getItemCount(): Int = cartProductList.count() + 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CART_PRODUCT_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_checkout_product, parent, false)
                CartProductHolder(view).setResource(parent.context)
            }
            USER_INFO_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_checkout_user_info, parent, false)
                UserInfoHolder(view).setResource(parent.context)
            }
            else -> {
                throw Exception("ViewType not match")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is CartProductHolder -> {
                holder.setResult(cartProductList[position])
            }
            is UserInfoHolder -> {
                holder.setResult(productAmountTotal, productNumber)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == cartProductList.count()) {
            USER_INFO_TYPE
        } else {
            CART_PRODUCT_TYPE
        }
    }

    fun bindCartProductList(cartProductList: List<CartProduct>) {
        this.cartProductList = cartProductList

        calculateAmount(cartProductList)

        notifyDataSetChanged()
    }

   private fun calculateAmount(cartProductList: List<CartProduct>) {
       var productTotal = 0
       cartProductList.forEach {
           productTotal += it.price * it.selectedStock
       }
       productAmountTotal = productTotal
       productNumber = cartProductList.count()
    }
}