package com.guanhong.stylish.ui.checkout

import android.content.Context
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.*
import com.guanhong.stylish.R
import android.widget.RadioButton
import com.guanhong.stylish.model.Order
import com.guanhong.stylish.model.OrderCheckout
import com.guanhong.stylish.model.Recipient
import com.guanhong.stylish.util.hide
import com.guanhong.stylish.util.show
import tech.cherri.tpdirect.api.TPDCard
import tech.cherri.tpdirect.api.TPDServerType
import tech.cherri.tpdirect.api.TPDSetup
import tech.cherri.tpdirect.model.TPDStatus


class UserInfoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var context: Context
    private lateinit var listener: UserInfoHolderListener

    private var tapPayCard: TPDCard? = null
    private var nameEditText = itemView.findViewById<EditText>(R.id.nameEditText)
    private var emailEditText = itemView.findViewById<EditText>(R.id.emailEditText)
    private var phoneEditText = itemView.findViewById<EditText>(R.id.phoneEditText)
    private var addressEditText = itemView.findViewById<EditText>(R.id.addressEditText)
    private var radioGroup = itemView.findViewById<RadioGroup>(R.id.radioGroup)
    private var checkoutConfirmLayout = itemView.findViewById<ConstraintLayout>(R.id.checkoutConfirmLayout)
    private var paymentSpinner = itemView.findViewById<Spinner>(R.id.spinner)
    private var productAmountTotal = itemView.findViewById<TextView>(R.id.productTotalAmount)
    private var transportAmount = itemView.findViewById<TextView>(R.id.transportAmount)
    private var totalAmount = itemView.findViewById<TextView>(R.id.totalAmount)
    private var totalAmountText = itemView.findViewById<TextView>(R.id.totalAmountText)
    private var tpdForm = itemView.findViewById<tech.cherri.tpdirect.api.TPDForm>(R.id.tpdForm)

    private var transportMoney = 60
    private var deliverTime = ""
    private var paymentMethod = ""
    private var tapPayPrimeKey = ""
    private var isTapPayCanGetPrime = false

    interface UserInfoHolderListener {
        fun orderCheckout(orderCheckout: OrderCheckout)
    }

    fun setResource(context: Context, listener: UserInfoHolderListener): RecyclerView.ViewHolder {
        this.context = context
        this.listener = listener
        return this
    }

    fun setResult(productAmountTotal: Int, productNumber: Int) {

        setSpinner()
        setTapPay()
        this.productAmountTotal.text = "NT $ " + productAmountTotal.toString()
        this.transportAmount.text = "NT $ " + transportMoney
        this.totalAmountText.text = "總計 ( " + productNumber + " 樣商品)"
        this.totalAmount.text = "NT $ " + (productAmountTotal + transportMoney)

        radioGroup.check(R.id.productId)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->

            val radioButton = itemView.findViewById(checkedId) as RadioButton
            val radioButtonIndex = group.indexOfChild(radioButton)
            when (radioButtonIndex) {
                0 -> {
                    deliverTime = "morning"
                }
                1 -> {
                    deliverTime = "afternoon"
                }
                2 -> {
                    deliverTime = "anytime"
                }
            }
        }

        checkoutConfirmLayout.setOnClickListener {
            checkUserInfo()
        }
    }

    private fun checkUserInfo() {

        if (nameEditText.text.toString().isBlank()) Toast.makeText(context, "請輸入" + nameEditText.hint, Toast.LENGTH_SHORT).show()
        else if (emailEditText.text.toString().isBlank()) Toast.makeText(context, "請輸入" + emailEditText.hint, Toast.LENGTH_SHORT).show()
        else if (phoneEditText.text.toString().isBlank()) Toast.makeText(context, "請輸入" + phoneEditText.hint, Toast.LENGTH_SHORT).show()
        else if (addressEditText.text.toString().isBlank()) Toast.makeText(context, "請輸入" + addressEditText.hint, Toast.LENGTH_SHORT).show()
        else if (deliverTime == "") Toast.makeText(context, "請選擇配送時間", Toast.LENGTH_SHORT).show()
        else if (paymentMethod == "") Toast.makeText(context, "請選擇付款方式", Toast.LENGTH_SHORT).show()
        else if (!isTapPayCanGetPrime) Toast.makeText(context, "信用卡資訊錯誤", Toast.LENGTH_SHORT).show()
        else {
            tapPayCard!!.getPrime()
        }
    }

    private fun setTapPay() {

        Log.d("Huang", " tapPay init")
        TPDSetup.initInstance(
                context,
                13576,
                "app_qxQxKMypOWWO81WAqUnENpRyrrR92wayyxiQLlNs5DPJW1bjps7WpFoLZlWj",
                TPDServerType.Sandbox)

        tpdForm.setTextErrorColor(Color.RED)
        tpdForm.setOnFormUpdateListener { tpdStatus ->

            if (tpdStatus.cardNumberStatus != TPDStatus.STATUS_OK ||
                    tpdStatus.expirationDateStatus != TPDStatus.STATUS_OK ||
                    tpdStatus.ccvStatus != TPDStatus.STATUS_OK) {
            }

            isTapPayCanGetPrime = tpdStatus.isCanGetPrime
        }
        tapPayCard = TPDCard.setup(tpdForm).onSuccessCallback { prime, _ ->

            tapPayPrimeKey = prime
            orderCheckout()
            Log.d("Huang", " onSuccessCallback   ")
        }.onFailureCallback { status, string ->
            Log.d("Huang", " status = " + status)
            Log.d("Huang", " string = " + string)
        }
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
                if (select != "付款方式") {
                    paymentMethod = select
                }
                if (select == "TapPay") {
                    tpdForm.show()
                } else {
                    tpdForm.hide()
                }
            }
        }
    }

    private fun orderCheckout() {

//        val recipient = Recipient()
//
//        with(recipient) {
//
//            name = nameEditText.text.toString()
//            email = emailEditText.text.toString()
//            phone = phoneEditText.text.toString()
//            address = addressEditText.text.toString()
//            time = deliverTime
//        }

        val order = Order()
        order.shipping = "delivery"
        order.payment = "credit_card"
//        order.subtotal = productAmountTotal.text.toString().toInt()
        order.freight = transportMoney
        order.total = productAmountTotal.text.toString().toInt() + transportMoney
//        order.recipient = recipient


//        val orderCheckout = OrderCheckout()
//        with(orderCheckout){
//            prime = tapPayPrimeKey
//            this.order = order
//        }

//        listener.orderCheckout(orderCheckout)
    }
}
