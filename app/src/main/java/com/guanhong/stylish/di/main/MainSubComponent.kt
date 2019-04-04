package com.guanhong.stylish.di.main

import com.guanhong.stylish.ui.main.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [MainPresenterModule::class])
interface MainSubComponent : AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<MainActivity>()
}