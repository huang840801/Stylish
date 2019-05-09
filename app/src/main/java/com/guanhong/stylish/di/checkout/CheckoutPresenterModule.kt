package com.guanhong.stylish.di.checkout

import com.guanhong.stylish.repository.CheckoutRepository
import com.guanhong.stylish.ui.checkout.CheckoutContract
import com.guanhong.stylish.ui.checkout.CheckoutFragment
import dagger.Module
import dagger.Provides

@Module
class CheckoutPresenterModule {
    @Provides
    fun checkoutFragmentModelProvider() : CheckoutRepository{
        return CheckoutRepository()
    }

    @Provides
    fun viewProvider(fragment: CheckoutFragment):CheckoutContract.View{
        return fragment
    }
}