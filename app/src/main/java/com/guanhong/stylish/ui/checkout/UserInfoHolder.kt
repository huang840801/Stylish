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
import com.guanhong.stylish.util.hide
import com.guanhong.stylish.util.show
import kotlinx.android.synthetic.main.activity_tappay.*
import tech.cherri.tpdirect.api.TPDCard
import tech.cherri.tpdirect.api.TPDServerType
import tech.cherri.tpdirect.api.TPDSetup
import tech.cherri.tpdirect.model.TPDStatus
import kotlin.properties.Delegates


class UserInfoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var context: Context

    private var tapPayCard: TPDCard? = null
    private var name = itemView.findViewById<EditText>(R.id.name)
    private var email = itemView.findViewById<EditText>(R.id.email)
    private var phoneNumber = itemView.findViewById<EditText>(R.id.phone)
    private var address = itemView.findViewById<EditText>(R.id.address)
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

    fun setResource(context: Context): RecyclerView.ViewHolder {
        this.context = context
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

        if (name.text.toString().isBlank()) Toast.makeText(context, "請輸入" + name.hint, Toast.LENGTH_SHORT).show()
        else if (email.text.toString().isBlank()) Toast.makeText(context, "請輸入" + email.hint, Toast.LENGTH_SHORT).show()
        else if (phoneNumber.text.toString().isBlank()) Toast.makeText(context, "請輸入" + phoneNumber.hint, Toast.LENGTH_SHORT).show()
        else if (address.text.toString().isBlank()) Toast.makeText(context, "請輸入" + address.hint, Toast.LENGTH_SHORT).show()
        else if (deliverTime == "") Toast.makeText(context, "請選擇配送時間", Toast.LENGTH_SHORT).show()
        else if (paymentMethod == "") Toast.makeText(context, "請選擇付款方式", Toast.LENGTH_SHORT).show()
        else if (!isTapPayCanGetPrime) Toast.makeText(context, "信用卡資訊錯誤", Toast.LENGTH_SHORT).show()
        else {
            getTapPayPrimeKey()
        }
    }

    private fun getTapPayPrimeKey() {

        tapPayCard!!.getPrime()


    }

    private fun setTapPay() {

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
            checkoutConfirm()
            Log.d("Huang", " onSuccessCallback tapPayPrimeKey = " + prime)
        }.onFailureCallback { status, _ ->
            Log.d("Huang", " status = " + status)
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

    private fun checkoutConfirm() {
        Log.d("Huang", " name = " + name.text)
        Log.d("Huang", " email = " + email.text)
        Log.d("Huang", " phine = " + phoneNumber.text)
        Log.d("Huang", " address = " + address.text)
        Log.d("Huang", " deliverTime = " + deliverTime)
        Log.d("Huang", " paymentMethod = " + paymentMethod)
        Log.d("Huang", " total = " + totalAmount.text)
        Log.d("Huang", " tapPayPrimeKey = " + tapPayPrimeKey)
    }
}
