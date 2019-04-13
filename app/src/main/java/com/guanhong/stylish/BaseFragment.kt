package com.guanhong.stylish

import android.support.v4.app.Fragment
import android.view.View

open class BaseFragment :Fragment() {

    fun View.show(){
        visibility = View.VISIBLE
    }
    fun View.hide(){
        visibility = View.GONE
    }
}