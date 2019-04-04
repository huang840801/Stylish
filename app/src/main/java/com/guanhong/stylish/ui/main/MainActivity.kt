package com.guanhong.stylish.ui.main

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
import android.view.MenuItem
import android.view.View
import com.guanhong.stylish.ui.cart.CartFragment
import com.guanhong.stylish.ui.catalog.CatalogFragment
import com.guanhong.stylish.ui.home.HomeFragment
import com.guanhong.stylish.ui.profile.ProfileFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.notification_badge.view.*

class MainActivity
    : BaseActivity(),
        MainContract.View,
        BottomNavigationView.OnNavigationItemSelectedListener,
HasSupportFragmentInjector{

    @Inject
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var homeFragment: HomeFragment
    private lateinit var catalogFragment: CatalogFragment
    private lateinit var cartFragment: CartFragment
    private lateinit var profileFragment: ProfileFragment

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
        setToolbarTitle("Stylish")
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_home -> {
                transToFragment(HOME)
                setToolbarTitle(getString(R.string.stylish))
            }
            R.id.action_catalog -> {
                transToFragment(CATALOG)
                setToolbarTitle(getString(R.string.catalog))
            }
            R.id.action_cart -> {
                transToFragment(CART)
                setToolbarTitle(getString(R.string.cart))
            }

            R.id.action_profile -> {
                transToFragment(PROFILE)
                setToolbarTitle(getString(R.string.profile))
            }
        }
        return true
    }

    private fun setToolbarTitle(title: String) {
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
        badge.notificationBadge.visible()
    }

    private fun View.visible() {
        visibility = View.VISIBLE
    }

    private fun View.invisible() {
        visibility = View.GONE
    }
}
