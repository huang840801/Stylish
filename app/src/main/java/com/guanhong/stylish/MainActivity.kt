package com.guanhong.stylish

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.guanhong.stylish.di.DaggerMainActivityComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMainActivityComponent.create().inject(this)
    }
}
