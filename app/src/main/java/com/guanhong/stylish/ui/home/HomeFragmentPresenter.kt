package com.guanhong.stylish.ui.home

import com.guanhong.stylish.repository.HotsRepository
import javax.inject.Inject

class HomeFragmentPresenter @Inject constructor(
        private val repository: HotsRepository,
        private val view: HomeFragmentContract.View)
    : HomeFragmentContract.Presenter {

    override fun getMarketingHots() {
       val hotsResponse =  repository.getMarketingHots()

        if (hotsResponse != null) {
            view.onBindMarketingHots(hotsResponse)
        }
    }
}