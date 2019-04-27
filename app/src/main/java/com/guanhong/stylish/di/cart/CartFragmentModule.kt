package com.guanhong.stylish.di.cart

import android.support.v4.app.Fragment
import com.guanhong.stylish.ui.cart.CartFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module
abstract class CartFragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(CartFragment::class)
    abstract fun bind(builder: CartSubComponent.Builder): AndroidInjector.Factory<out Fragment>
}