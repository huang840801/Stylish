package com.guanhong.stylish.di

import com.guanhong.stylish.Stylish
import com.guanhong.stylish.di.add2cart.AddCartFragmentModule
import com.guanhong.stylish.di.cart.CartFragmentModule
import com.guanhong.stylish.di.catalog.CatalogFragmentModule
import com.guanhong.stylish.di.catalog.CatalogPresenterModule
import com.guanhong.stylish.di.checkout.CheckoutFragmentModule
import com.guanhong.stylish.di.child.CatalogChildFragmentModule
import com.guanhong.stylish.di.home.HomeFragmentModule
import com.guanhong.stylish.di.main.MainActivityModule
import com.guanhong.stylish.ui.catalog.child.CatalogChildFragment
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    MainActivityModule::class,
    HomeFragmentModule::class,
    CatalogFragmentModule::class,
    CatalogChildFragmentModule::class,
    AddCartFragmentModule::class,
    CartFragmentModule::class,
    CheckoutFragmentModule::class])
interface AppComponent {
    fun inject(application: Stylish)
}