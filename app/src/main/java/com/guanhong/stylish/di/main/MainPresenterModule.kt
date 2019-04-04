package com.guanhong.stylish.di.main

import com.guanhong.stylish.ui.main.MainActivity
import com.guanhong.stylish.ui.main.MainContract
import dagger.Module
import dagger.Provides

@Module
class MainPresenterModule {

    @Provides
    fun viewProvider(activity: MainActivity): MainContract.View {
        return activity
    }
}