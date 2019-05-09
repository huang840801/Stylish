package com.guanhong.stylish.di.checkout

import com.guanhong.stylish.ui.checkout.CheckoutFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [CheckoutPresenterModule::class])
interface CheckoutSubComponent :AndroidInjector<CheckoutFragment>{
    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<CheckoutFragment>()
}