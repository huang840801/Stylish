package com.guanhong.stylish.di

import com.guanhong.stylish.Stylish
import com.guanhong.stylish.di.home.HomeFragmentModule
import com.guanhong.stylish.di.main.MainActivityModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    MainActivityModule::class,
    HomeFragmentModule::class])
interface AppComponent {
    fun inject(application: Stylish)
}