package com.guanhong.stylish.ui.main

import android.content.Context
import android.os.Bundle
import com.guanhong.stylish.BaseActivity
import com.guanhong.stylish.R
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.view.LayoutInflater
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.util.Log
import android.view.MenuItem
import com.guanhong.stylish.Stylish
import com.guanhong.stylish.api.ApiHelper
import com.guanhong.stylish.api.DataResourceCallback
import com.guanhong.stylish.ui.LoginSheetDialogFragment
import com.guanhong.stylish.ui.cart.CartFragment
import com.guanhong.stylish.ui.catalog.CatalogFragment
import com.guanhong.stylish.ui.home.HomeFragment
import com.guanhong.stylish.ui.profile.ProfileFragment
import com.guanhong.stylish.util.UserManager
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.notification_badge.view.*

class MainActivity
    : BaseActivity(),
        MainContract.View,
        BottomNavigationView.OnNavigationItemSelectedListener,
        HasSupportFragmentInjector,
        LoginSheetDialogFragment.LoginSheetDialogFragmentListener {

    @Inject
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var homeFragment: HomeFragment
    private lateinit var catalogFragment: CatalogFragment
    private lateinit var cartFragment: CartFragment
    private lateinit var profileFragment: ProfileFragment

    private var loginSheetDialogFragment: LoginSheetDialogFragment? = null

    private val HOME = "home"
    private val CATALOG = "catalog"
    private val CART = "cart"
    private val PROFILE = "profile"

    companion object {
        const val FRAGMENT_HOME = "home"
        const val FRAGMENT_CATALOG = "catalog"
        const val FRAGMENT_CART = "cart"
        const val FRAGMENT_PROFILE = "profile"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.test()

        toolbar.setPadding(0, getStatusBarHeight(), 0, 0)
        bottomNavigation.setOnNavigationItemSelectedListener(this)

        homeFragment = HomeFragment().newInstance()
        catalogFragment = CatalogFragment().newInstance()
        cartFragment = CartFragment().newInstance()
        profileFragment = ProfileFragment().newInstance()

        transToFragment(HOME)
        toolbarLogo.show()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_home -> {
                transToFragment(HOME)
                toolbarTitle.hide()
                toolbarLogo.show()
            }
            R.id.action_catalog -> {
                transToFragment(CATALOG)
                setToolbarTitle(getString(R.string.catalog))
                toolbarLogo.hide()
            }
            R.id.action_cart -> {
                transToFragment(CART)
                setToolbarTitle(getString(R.string.cart))
                toolbarLogo.hide()
            }

            R.id.action_profile -> {
                transToFragment(PROFILE)
                setToolbarTitle(getString(R.string.profile))
                toolbarLogo.hide()
                checkLogin()
            }
        }
        return true
    }

    override fun loginSuccess() {
        dismissLoginSheetDialogFragment()
    }

    private fun checkLogin() {

        val stylishToken = Stylish.context.getSharedPreferences(UserManager.USER_DATA, Context.MODE_PRIVATE)
                .getString(UserManager.USER_TOKEN, null)

        if (!stylishToken.isNullOrEmpty()) {

            ApiHelper().getUserSignIn(stylishToken, object : DataResourceCallback.GetUserSignIn {
                override fun onSuccess() {
                    dismissLoginSheetDialogFragment()
                }
            })
        } else {

            loginSheetDialogFragment = LoginSheetDialogFragment()
            loginSheetDialogFragment!!.setListener(this)
            loginSheetDialogFragment!!.show(supportFragmentManager, "FbLogin")
        }
    }

    private fun dismissLoginSheetDialogFragment() {

        if (loginSheetDialogFragment != null) {
            loginSheetDialogFragment!!.dismiss()
        }
    }

    private fun setToolbarTitle(title: String) {
        toolbarTitle.show()
        toolbarTitle.text = title
    }

    private fun transToFragment(fragmentType: String) {

        val fragmentManager = supportFragmentManager.beginTransaction()

        when (fragmentType) {

            HOME -> {
                fragmentManager.hide(catalogFragment)
                fragmentManager.hide(cartFragment)
                fragmentManager.hide(profileFragment)

                if (homeFragment.isAdded) {
                    fragmentManager.show(homeFragment)
                } else {
                    fragmentManager.add(R.id.container, homeFragment)
                }
            }
            CATALOG -> {
                fragmentManager.hide(homeFragment)
                fragmentManager.hide(cartFragment)
                fragmentManager.hide(profileFragment)

                if (catalogFragment.isAdded) {
                    fragmentManager.show(catalogFragment)
                } else {
                    fragmentManager.add(R.id.container, catalogFragment)
                    fragmentManager.show(catalogFragment)
                }
            }
            CART -> {
                fragmentManager.hide(homeFragment)
                fragmentManager.hide(catalogFragment)
                fragmentManager.hide(profileFragment)

                if (cartFragment.isAdded) {
                    fragmentManager.show(cartFragment)
                } else {
                    fragmentManager.add(R.id.container, cartFragment)
                    fragmentManager.show(cartFragment)
                }
            }
            PROFILE -> {
                fragmentManager.hide(homeFragment)
                fragmentManager.hide(catalogFragment)
                fragmentManager.hide(cartFragment)

                if (profileFragment.isAdded) {
                    fragmentManager.show(profileFragment)
                } else {

                    fragmentManager.add(R.id.container, profileFragment)
                    fragmentManager.show(profileFragment)

                }
            }
        }
        fragmentManager.commit()
    }

    private fun setNotificationBadge() {

        val bottomNavigationMenuView = bottomNavigation.getChildAt(0) as BottomNavigationMenuView
        val v = bottomNavigationMenuView.getChildAt(2)
        val itemView = v as BottomNavigationItemView
        val badge = LayoutInflater.from(this)
                .inflate(R.layout.notification_badge, itemView, true)

        badge.notificationBadge.text = ""
        badge.notificationBadge.show()
    }
}
