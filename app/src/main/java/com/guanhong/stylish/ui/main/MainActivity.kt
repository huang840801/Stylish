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
import com.guanhong.stylish.model.CartProduct
import com.guanhong.stylish.model.Product
import com.guanhong.stylish.ui.LoginDialogFragment
import com.guanhong.stylish.ui.cart.CartFragment
import com.guanhong.stylish.ui.catalog.CatalogFragment
import com.guanhong.stylish.ui.checkout.CheckoutFragment
import com.guanhong.stylish.ui.checkout.CheckoutFragment.Companion.PRODUCT_COUNT
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
        LoginDialogFragment.LoginSheetDialogFragmentListener,
        CatalogFragment.CatalogFragmentListener,
        HomeFragment.HomeFragmentListener,
        DetailFragment.DetailFragmentListener,
        CartFragment.CartFragmentListener,
        CheckoutFragment.CheckoutFragmentListener {

    @Inject
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var homeFragment: HomeFragment
    private lateinit var catalogFragment: CatalogFragment
    private lateinit var cartFragment: CartFragment
    private lateinit var profileFragment: ProfileFragment
    private lateinit var detailFragment: DetailFragment
    private lateinit var checkoutFragment: CheckoutFragment

    private var loginDialogFragment: LoginDialogFragment? = null

    private val home = "home"
    private val catalog = "catalog"
    private val cart = "cart"
    private val profile = "profile"
    private val detail = "detail"
    private val checkout = "checkout"

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
        cartFragment.setListener(this)
        profileFragment = ProfileFragment().newInstance()
        detailFragment = DetailFragment().newInstance()
        detailFragment.setListener(this)
        checkoutFragment = CheckoutFragment().newInstance()
        checkoutFragment.setListener(this)

        transToFragment(home, null, null)
        setToolbar("")
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_home -> {
                transToFragment(home, null, null)
                setToolbar("")
            }
            R.id.action_catalog -> {
                transToFragment(catalog, null, null)
                setToolbar(getString(R.string.catalog))
            }
            R.id.action_cart -> {
                transToFragment(cart, null, null)
                setToolbar(getString(R.string.cart))
            }

            R.id.action_profile -> {
                transToFragment(profile, null, null)
                setToolbar(getString(R.string.profile))
                checkLogin()
            }
        }
        return true
    }

    override fun checkoutClick(cartProductList: List<CartProduct>) {
        transToFragment(checkout, null, cartProductList)
    }

    override fun loginSuccess() {
        dismissLoginSheetDialogFragment()
    }

    override fun itemClick(product: Product) {
        transToFragment(detail, product, null)
    }

    override fun addToCart() {
        cartFragment.updateCartProduct()
    }

    override fun detailFragmentCreate() {
        toolbar.hide()
        bottomNavigation.hide()
    }

    override fun detailFragmentDestroy() {
        toolbar.show()
        bottomNavigation.show()
    }

    override fun checkoutFragmentCreate() {
        toolbar.hide()
        bottomNavigation.hide()
    }

    override fun checkoutFragmentDestroy() {
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

            loginDialogFragment = LoginDialogFragment()
            loginDialogFragment!!.setListener(this)
            loginDialogFragment!!.show(supportFragmentManager, "FbLogin")
        }
    }

    private fun dismissLoginSheetDialogFragment() {

        if (loginDialogFragment != null) {
            loginDialogFragment!!.dismiss()
        }
        updateUserData()
    }

    private fun updateUserData() {
        profileFragment.setUserData()
    }

    private fun setToolbar(title: String) {

        if (title == "") {
            toolbarLogo.show()
            toolbarTitle.hide()

        } else {
            toolbarLogo.hide()
            toolbarTitle.show()
            toolbarTitle.text = title
        }
    }

    private fun transToFragment(fragmentType: String, product: Product?, cartProductList: List<CartProduct>?) {

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        when (fragmentType) {

            home -> {
                transaction.hide(catalogFragment)
                transaction.hide(cartFragment)
                transaction.hide(profileFragment)
                transaction.hide(detailFragment)

                if (homeFragment.isAdded) {
                    transaction.show(homeFragment)
                } else {
                    transaction.add(R.id.container, homeFragment)
                }
            }
            catalog -> {
                transaction.hide(homeFragment)
                transaction.hide(cartFragment)
                transaction.hide(profileFragment)
                transaction.hide(detailFragment)

                if (catalogFragment.isAdded) {
                    transaction.show(catalogFragment)
                } else {
                    transaction.add(R.id.container, catalogFragment)
                    transaction.show(catalogFragment)
                }
            }
            cart -> {
                transaction.hide(homeFragment)
                transaction.hide(catalogFragment)
                transaction.hide(profileFragment)
                transaction.hide(detailFragment)

                if (cartFragment.isAdded) {
                    transaction.show(cartFragment)
                } else {
                    transaction.add(R.id.container, cartFragment)
                    transaction.show(cartFragment)
                }
            }
            profile -> {
                transaction.hide(homeFragment)
                transaction.hide(catalogFragment)
                transaction.hide(cartFragment)
                transaction.hide(detailFragment)

                if (profileFragment.isAdded) {
                    transaction.show(profileFragment)
                } else {

                    transaction.add(R.id.container, profileFragment)
                    transaction.show(profileFragment)

                }
            }
            detail -> {

                for (element in fragmentManager.fragments) {
                    if (!element.isHidden) {
                        transaction.hide(element)
                        transaction.addToBackStack(null)
                        break
                    }
                }
                if (product != null) {
                    val bundle = Bundle()

                    bundle.putSerializable("product", product)
                    detailFragment.arguments = bundle
                }

                if (detailFragment.isAdded) {
                    transaction.show(detailFragment)
                } else {

                    transaction.add(R.id.container, detailFragment)
                    transaction.show(detailFragment)
                }
            }
            checkout -> {

                for (element in fragmentManager.fragments) {
                    if (!element.isHidden) {
                        transaction.hide(element)
                        transaction.addToBackStack(null)
                        break
                    }
                }
                if (cartProductList != null) {

                    val bundle = Bundle()
                    bundle.putInt(PRODUCT_COUNT, cartProductList.count())

                    cartProductList.forEachIndexed { index, cartProduct ->

                        bundle.putSerializable("product" + index, cartProduct)
                    }
                    checkoutFragment.arguments = bundle
                }

                if (checkoutFragment.isAdded) {
                    transaction.show(checkoutFragment)
                } else {

                    transaction.add(R.id.container, checkoutFragment)
                    transaction.show(checkoutFragment)
                }
            }
        }
        transaction.commit()
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
