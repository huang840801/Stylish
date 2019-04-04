package com.guanhong.stylish.di.main

import android.app.Activity
import com.guanhong.stylish.di.main.MainSubComponent
import com.guanhong.stylish.ui.main.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun bind(builder: MainSubComponent.Builder): AndroidInjector.Factory<out Activity>
}