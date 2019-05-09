package com.guanhong.stylish.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import com.guanhong.stylish.BaseActivity
import com.guanhong.stylish.R
import kotlinx.android.synthetic.main.tappay.*
import tech.cherri.tpdirect.api.TPDCard
import tech.cherri.tpdirect.api.TPDServerType
import tech.cherri.tpdirect.api.TPDSetup
import tech.cherri.tpdirect.model.TPDStatus

class TapPay :BaseActivity() {

    private var tapPayCard: TPDCard? = null
    private var isTapPayCanGetPrime = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tappay)
        buttontappay.setOnClickListener {

            tapPayCard!!.getPrime()
        }
        TPDSetup.initInstance(
                this,
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
            Log.d("Huang", " onSuccessCallback tapPayPrimeKey = " + prime)
        }.onFailureCallback { status, string ->
            Log.d("Huang", " status = " + status)
            Log.d("Huang", " string = " + string)
        }
    }
}