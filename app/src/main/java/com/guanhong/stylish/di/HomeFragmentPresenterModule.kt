package com.guanhong.stylish.di

import com.guanhong.stylish.repository.interface1.IHotsRepository
import com.guanhong.stylish.ui.home.HomeFragmentPresenter
import dagger.Module
import dagger.Provides

@Module
class HomeFragmentPresenterModule(private val repository: IHotsRepository) {

    @Provides
    fun homeFragmentPresenterProvider(): HomeFragmentPresenter {
        return HomeFragmentPresenter(repository)
    }
}