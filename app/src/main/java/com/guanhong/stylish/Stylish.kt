package com.guanhong.stylish

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.app.Fragment
import android.content.Context
import com.guanhong.stylish.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class Stylish : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingFragmentInjector: DispatchingAndroidInjector<android.support.v4.app.Fragment>

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var context : Context
    }
    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<android.support.v4.app.Fragment> {
        return dispatchingFragmentInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.create().inject(this)

        context = this
    }
}