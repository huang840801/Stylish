package com.guanhong.stylish.di.add2cart

import com.guanhong.stylish.repository.AddCartRepository
import com.guanhong.stylish.ui.add2cart.AddCartContract
import com.guanhong.stylish.ui.add2cart.AddCartFragment
import dagger.Module
import dagger.Provides

@Module
class AddCartPresenterModule {

    @Provides
    fun addCartFragmentModelProvider(): AddCartRepository{
        return AddCartRepository()
    }

    @Provides
    fun viewProvider(fragment: AddCartFragment): AddCartContract.View{
        return fragment
    }
}