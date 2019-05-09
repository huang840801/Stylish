package com.guanhong.stylish.di.checkout

import android.support.v4.app.Fragment
import com.guanhong.stylish.ui.checkout.CheckoutFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module
abstract class CheckoutFragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(CheckoutFragment::class)
    abstract fun bind(builder: CheckoutSubComponent.Builder) : AndroidInjector.Factory<out Fragment>
}