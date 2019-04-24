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
import com.guanhong.stylish.api.DataResourceCallback
import kotlinx.android.synthetic.main.dialog_login.*
import java.util.*


class LoginDialogFragment : BottomSheetDialogFragment() {

    private lateinit var callbackManager: CallbackManager
    private lateinit var listener: LoginSheetDialogFragmentListener

    private var isLogin = false

    interface LoginSheetDialogFragmentListener{
        fun loginSuccess()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FacebookSdk.sdkInitialize(context)
        val accessToken = AccessToken.getCurrentAccessToken()
        isLogin = accessToken != null && !accessToken.isExpired

        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {

                val facebookToken = result!!.accessToken.token
                ApiHelper().getUserSignIn(facebookToken, object :DataResourceCallback.GetUserSignIn{
                    override fun onSuccess() {
                        Log.d("Huang", " fb login onSuccess")
                        listener.loginSuccess()
                    }
                })
            }

            override fun onCancel() {
                Log.d("Huang", " onCancel ")
            }

            override fun onError(error: FacebookException?) {
                Log.d("Huang", " onError ")
            }
        })
        loginLayout.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"))
        }
        close.setOnClickListener{
            this.dismiss()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun isLogin(): Boolean = this.isLogin
    fun setListener(listener: LoginSheetDialogFragmentListener) {
        this.listener = listener
    }
}

