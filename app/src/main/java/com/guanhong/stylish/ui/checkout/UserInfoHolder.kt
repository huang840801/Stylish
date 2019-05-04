package com.guanhong.stylish.ui.checkout

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.guanhong.stylish.R

class UserInfoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var context: Context
    private var paymentSpinner = itemView.findViewById<Spinner>(R.id.spinner)
    private var productAmountTotal = itemView.findViewById<TextView>(R.id.productTotalAmount)
    private var transportAmount = itemView.findViewById<TextView>(R.id.transportAmount)
    private var totalAmount = itemView.findViewById<TextView>(R.id.totalAmount)
    private var totalAmountText = itemView.findViewById<TextView>(R.id.totalAmountText)
    private var transportMoney = 60

    fun setResource(context: Context): RecyclerView.ViewHolder {
        this.context = context
        return this
    }

    fun setResult(productAmountTotal: Int, productNumber: Int) {
        setSpinner()
        this.productAmountTotal.text = "NT $ " + productAmountTotal.toString()
        this.transportAmount.text = "NT $ " + transportMoney
        this.totalAmountText.text = "總計 ( " + productNumber + " 樣商品)"
        this.totalAmount.text = "NT $ " + (productAmountTotal + transportMoney)

    }

    private fun setSpinner() {

        val payment = arrayOf("付款方式", "TapPay")
        val paymentList: ArrayAdapter<String> = ArrayAdapter(context,
                android.R.layout.simple_spinner_dropdown_item,
                payment)
        paymentSpinner.adapter = paymentList

        paymentSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val select = parent!!.getItemAtPosition(position).toString()
            }
        }
    }
}
