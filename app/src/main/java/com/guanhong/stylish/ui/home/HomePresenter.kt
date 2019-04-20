package com.guanhong.stylish.ui.home

import com.guanhong.stylish.model.response.HotResponse
import com.guanhong.stylish.api.DataResourceCallback
import com.guanhong.stylish.repository.HotsRepository
import javax.inject.Inject

class HomePresenter @Inject constructor(
        private val repository: HotsRepository,
        private val view: HomeContract.View)
    : HomeContract.Presenter {

    override fun getMarketingHots() {
        repository.getMarketingHots(object : DataResourceCallback.GetMarketingHots {
            override fun onSuccess(response: HotResponse) {

                val hotList = ArrayList<Any>()

                response.data.forEach { hots->
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