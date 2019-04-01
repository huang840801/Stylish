package com.guanhong.stylish.ui.home

import com.guanhong.stylish.repository.interface1.IHotsRepository
import javax.inject.Inject

class HomeFragmentPresenter @Inject constructor(private val repository: IHotsRepository): HomeFragmentContract.Presenter {

    override fun getMarketingHots() {
    }
}