package com.guanhong.stylish.di.home

import com.guanhong.stylish.repository.HotsRepository
import com.guanhong.stylish.ui.home.HomeFragment
import com.guanhong.stylish.ui.home.HomeContract
import dagger.Module
import dagger.Provides

@Module
class HomePresenterModule {

    @Provides
    fun homeFragmentModelProvider(): HotsRepository {
        return HotsRepository()
    }

    @Provides
    fun viewProvider(fragment: HomeFragment) : HomeContract.View{
        return fragment
    }
}