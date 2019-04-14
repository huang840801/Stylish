package com.guanhong.stylish.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.guanhong.stylish.R
import com.guanhong.stylish.api.ApiHelper
import kotlinx.android.synthetic.main.bottom_sheet.*
import java.util.*


class LoginSheetDialogFragment : BottomSheetDialogFragment() {

    private lateinit var callbackManager: CallbackManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bottom_sheet, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FacebookSdk.sdkInitialize(context)
        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired

        Log.d("Huang", " isLoggedIn = " + isLoggedIn)

        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                Log.d("Huang", " token = " + result!!.accessToken.token)
                val facebookToken = result.accessToken.token
                val apiHelper = ApiHelper()
                apiHelper.getUserSignIn(facebookToken)
            }

            override fun onCancel() {
                Log.d("Huang", " onCancel ")
            }

            override fun onError(error: FacebookException?) {
                Log.d("Huang", " onError ")
            }
        })
        loginLayout.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}

