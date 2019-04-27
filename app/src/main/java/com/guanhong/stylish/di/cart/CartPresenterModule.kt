package com.guanhong.stylish.di.cart

import com.guanhong.stylish.repository.CartRepository
import com.guanhong.stylish.ui.cart.CartContract
import com.guanhong.stylish.ui.cart.CartFragment
import dagger.Module
import dagger.Provides

@Module
class CartPresenterModule {

    @Provides
    fun cartFragmentModelProvider(): CartRepository {
        return CartRepository()
    }

    @Provides
    fun viewProvider(fragment : CartFragment): CartContract.View{
        return fragment
    }
}