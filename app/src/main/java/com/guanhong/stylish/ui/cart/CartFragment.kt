package com.guanhong.stylish.ui.cart

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guanhong.stylish.BaseFragment
import com.guanhong.stylish.R
import com.guanhong.stylish.model.CartProduct
import com.guanhong.stylish.util.hide
import com.guanhong.stylish.util.show
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_cart.*
import javax.inject.Inject

class CartFragment : BaseFragment(), CartContract.View, CartAdapter.CartAdapterListener {

    @Inject
    lateinit var presenter: CartPresenter

    private lateinit var adapter: CartAdapter

    override fun onAttach(activity: Activity?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    fun newInstance(): CartFragment {
        return CartFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CartAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        goCheckout.setOnClickListener {
            goCheckout()
        }

        getCartProductList()
    }

    override fun removeClick(productId: String) {

        val dialog = AlertDialog.Builder(context)

        dialog.setTitle("確定要刪除嗎?")
                .setPositiveButton("確定") { _, _ ->
                    presenter.deleteCartProduct(context!!, productId)
                }
                .setNegativeButton("取消") { _, _ -> }
                .show()
    }

    override fun showCartProductList(cartProducts: List<CartProduct>) {

        adapter.onBindCartList(cartProducts)
    }

    override fun updateCartProductList() {
        getCartProductList()
    }

    override fun showProgressBar() {
        progressBar.show()
    }

    override fun hideProgressBar() {
        progressBar.hide()
    }

    fun updateCartProduct() {
        getCartProductList()
    }

    private fun goCheckout() {

    }


    private fun getCartProductList() {
        presenter.getCartProductList(context!!)
    }
}