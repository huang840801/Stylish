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
import com.guanhong.stylish.model.Product
import com.guanhong.stylish.ui.LoginSheetDialogFragment
import com.guanhong.stylish.ui.cart.CartFragment
import com.guanhong.stylish.ui.catalog.CatalogFragment
import com.guanhong.stylish.ui.detail.DetailFragment
import com.guanhong.stylish.ui.home.HomeFragment
import com.guanhong.stylish.ui.profile.ProfileFragment
import com.guanhong.stylish.util.UserManager
import com.guanhong.stylish.util.hide
import com.guanhong.stylish.util.show
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
        LoginSheetDialogFragment.LoginSheetDialogFragmentListener,
        CatalogFragment.CatalogFragmentListener,
        HomeFragment.HomeFragmentListener,
        DetailFragment.DetailFragmentListener {

    @Inject
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var homeFragment: HomeFragment
    private lateinit var catalogFragment: CatalogFragment
    private lateinit var cartFragment: CartFragment
    private lateinit var profileFragment: ProfileFragment
    private lateinit var detailFragment: DetailFragment

    private var loginSheetDialogFragment: LoginSheetDialogFragment? = null

    private val home = "home"
    private val catalog = "catalog"
    private val cart = "cart"
    private val profile = "profile"
    private val detail = "detail"

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
        homeFragment.setListener(this)
        catalogFragment = CatalogFragment().newInstance()
        catalogFragment.setListener(this)
        cartFragment = CartFragment().newInstance()
        profileFragment = ProfileFragment().newInstance()
        detailFragment = DetailFragment().newInstance()
        detailFragment.setListener(this)

        transToFragment(home, null)
        toolbarLogo.show()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_home -> {
                transToFragment(home, null)
                toolbarTitle.hide()
                toolbarLogo.show()
            }
            R.id.action_catalog -> {
                transToFragment(catalog, null)
                setToolbarTitle(getString(R.string.catalog))
                toolbarLogo.hide()
            }
            R.id.action_cart -> {
                transToFragment(cart, null)
                setToolbarTitle(getString(R.string.cart))
                toolbarLogo.hide()
            }

            R.id.action_profile -> {
                transToFragment(profile, null)
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

    override fun itemClick(product: Product) {
        Log.d("Huang", "  itemClick ${product.title}")
        transToFragment(detail, product)
    }

    override fun detailFragmentCreate() {
        toolbar.hide()
        bottomNavigation.hide()
    }

    override fun detailFragmentDestroy() {
        toolbar.show()
        bottomNavigation.show()
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
        updateUserData()
    }

    private fun updateUserData() {
        profileFragment.setUserData()

    }

    private fun setToolbarTitle(title: String) {
        toolbarTitle.show()
        toolbarTitle.text = title
    }

    private fun transToFragment(fragmentType: String, product: Product?) {

        val fragmentManager = supportFragmentManager.beginTransaction()

        when (fragmentType) {

            home -> {
                fragmentManager.hide(catalogFragment)
                fragmentManager.hide(cartFragment)
                fragmentManager.hide(profileFragment)

                if (homeFragment.isAdded) {
                    fragmentManager.show(homeFragment)
                } else {
                    fragmentManager.add(R.id.container, homeFragment)
                }
            }
            catalog -> {
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
            cart -> {
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
            profile -> {
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
            detail -> {
                fragmentManager.hide(homeFragment)
                fragmentManager.hide(catalogFragment)
                fragmentManager.hide(cartFragment)
                fragmentManager.hide(profileFragment)

                if (product != null) {
                    val bundle = Bundle()
                    bundle.putSerializable("product", product)
                    detailFragment.arguments = bundle
                }

                if (detailFragment.isAdded) {
                    fragmentManager.show(detailFragment)
                } else {

                    fragmentManager.add(R.id.container, detailFragment)
                    fragmentManager.show(detailFragment)
                }

                fragmentManager.addToBackStack(null)
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
