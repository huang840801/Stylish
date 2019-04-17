package com.guanhong.stylish.di

import android.content.Context
import com.guanhong.stylish.Stylish
import com.guanhong.stylish.di.home.HomeSubComponent
import com.guanhong.stylish.di.main.MainSubComponent
import dagger.Module
import dagger.Provides

@Module(subcomponents = [
    MainSubComponent::class,
    HomeSubComponent::class])
class AppModule {

    @Provides
    fun contextProvider(application: Stylish): Context {
        return application.applicationContext
    }
}