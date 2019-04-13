package com.guanhong.stylish.ui.home

import com.guanhong.stylish.`object`.Data
import com.guanhong.stylish.api.DataResourceCallback
import com.guanhong.stylish.repository.HotsRepository
import javax.inject.Inject

class HomeFragmentPresenter @Inject constructor(
        private val repository: HotsRepository,
        private val view: HomeFragmentContract.View)
    : HomeFragmentContract.Presenter {

    override fun getMarketingHots() {
        repository.getMarketingHots(object : DataResourceCallback.GetMarketingHots {
            override fun onSuccess(data: Data) {

                val hotList = ArrayList<Any>()

                data.data.forEach { hots->
                    hotList.add(hots.title)
                    hots.products.forEach {products ->
                        hotList.add(products)
                    }
                }
                view.onBindMarketingHots(hotList)
            }
        })
    }
}