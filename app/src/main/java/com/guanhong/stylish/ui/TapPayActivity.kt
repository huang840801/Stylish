package com.guanhong.stylish.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import com.guanhong.stylish.BaseActivity
import com.guanhong.stylish.R
import kotlinx.android.synthetic.main.activity_tappay.*
import tech.cherri.tpdirect.api.TPDCard
import tech.cherri.tpdirect.api.TPDServerType
import tech.cherri.tpdirect.api.TPDSetup
import tech.cherri.tpdirect.model.TPDStatus

class TapPayActivity : BaseActivity() {

    private  var card: TPDCard? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tappay)

        payButton.isEnabled = false
        payButton.setOnClickListener {
            pay()
        }
        TPDSetup.initInstance(this,
                13576,
                "app_qxQxKMypOWWO81WAqUnENpRyrrR92wayyxiQLlNs5DPJW1bjps7WpFoLZlWj",
                TPDServerType.Sandbox)

        tpdForm.setTextErrorColor(Color.RED)
        tpdForm.setOnFormUpdateListener { tpdStatus ->

            if (tpdStatus.cardNumberStatus == TPDStatus.STATUS_ERROR) {
                Log.d("Huang", "Invalid Card Number" )

            } else if (tpdStatus.expirationDateStatus == TPDStatus.STATUS_ERROR) {
                Log.d("Huang",  "Invalid Expiration Date" )

            } else if (tpdStatus.ccvStatus == TPDStatus.STATUS_ERROR) {
                Log.d("Huang",  "Invalid CCV" )
            }
            payButton.isEnabled = tpdStatus.isCanGetPrime
        }

            card = TPDCard.setup(tpdForm)
                    .onSuccessCallback { prime, tpdCardInfo ->
                        Log.d("Huang", " prime = " + prime)
                        Log.d("Huang", " country = " + tpdCardInfo.country)

                    }
                    .onFailureCallback { status, message ->
                        Log.d("Huang", " status = " + status)
                        Log.d("Huang", " message = " + message)
                    }

    }

    private fun pay() {
        if (card != null) {
            card!!.getPrime()
        }
    }
}