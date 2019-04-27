package com.guanhong.stylish.di.cart

import com.guanhong.stylish.ui.cart.CartFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [CartPresenterModule::class])
interface CartSubComponent :AndroidInjector<CartFragment>{
    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<CartFragment>()
}