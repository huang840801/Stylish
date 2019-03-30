package com.guanhong.stylish.ui.main

import android.os.Bundle
import com.guanhong.stylish.BaseActivity
import com.guanhong.stylish.R
import com.guanhong.stylish.di.DaggerMainActivityComponent
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.view.LayoutInflater
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.view.View
import kotlinx.android.synthetic.main.notification_badge.view.*


class MainActivity : BaseActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerMainActivityComponent.create().inject(this)

        toolbarTitle.text = "Stylish"
        toolbar.setPadding(0, getStatusBarHeight(), 0, 0)
    }

    private fun setNotificationBadge() {

        val bottomNavigationMenuView = bottomNavigation.getChildAt(0) as BottomNavigationMenuView
        val v = bottomNavigationMenuView.getChildAt(2)
        val itemView = v as BottomNavigationItemView
        val badge = LayoutInflater.from(this)
                .inflate(R.layout.notification_badge, itemView, true)

        badge.notificationBadge.text = ""
        badge.notificationBadge.visible()
    }

    private fun View.visible() {
        visibility = View.VISIBLE
    }

    private fun View.invisible() {
        visibility = View.GONE
    }
}
