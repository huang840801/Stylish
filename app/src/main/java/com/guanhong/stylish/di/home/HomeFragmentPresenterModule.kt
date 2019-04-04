package com.guanhong.stylish.di.home

import com.guanhong.stylish.repository.HotsRepository
import com.guanhong.stylish.ui.home.HomeFragment
import com.guanhong.stylish.ui.home.HomeFragmentContract
import dagger.Module
import dagger.Provides

@Module
class HomeFragmentPresenterModule {

    @Provides
    fun homeFragmentModelProvider(): HotsRepository {
        return HotsRepository()
    }

    @Provides
    fun viewProvider(fragment: HomeFragment) : HomeFragmentContract.View{
        return fragment
    }
}