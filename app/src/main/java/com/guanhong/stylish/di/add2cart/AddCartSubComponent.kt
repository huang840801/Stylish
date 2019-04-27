package com.guanhong.stylish.di.add2cart

import com.guanhong.stylish.ui.add2cart.AddCartFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [AddCartPresenterModule::class])
interface AddCartSubComponent :AndroidInjector<AddCartFragment>{
    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<AddCartFragment>()
}