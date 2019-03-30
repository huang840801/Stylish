package com.guanhong.stylish.di

import com.guanhong.stylish.ui.main.MainActivity
import dagger.Component

@Component
interface MainActivityComponent {
    fun inject(activity: MainActivity)
}