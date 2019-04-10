package com.guanhong.stylish.ui.home

import android.util.Log
import com.guanhong.stylish.`object`.Hots
import com.guanhong.stylish.api.DataResourceCallback
import com.guanhong.stylish.repository.HotsRepository
import javax.inject.Inject

class HomeFragmentPresenter @Inject constructor(
        private val repository: HotsRepository,
        private val view: HomeFragmentContract.View)
    : HomeFragmentContract.Presenter {

    override fun getMarketingHots() {
        repository.getMarketingHots(object : DataResourceCallback.GetMarketingHots {
            override fun onSuccess(hotsList: List<Hots>) {
                view.onBindMarketingHots(hotsList)
            }
        })
    }
}