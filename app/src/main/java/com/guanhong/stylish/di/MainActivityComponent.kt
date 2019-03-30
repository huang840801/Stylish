package com.guanhong.stylish.di

import com.guanhong.stylish.MainActivity
import dagger.Component

@Component
interface MainActivityComponent {
    fun inject(activity: MainActivity)
}