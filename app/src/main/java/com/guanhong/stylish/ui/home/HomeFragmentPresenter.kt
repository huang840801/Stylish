package com.guanhong.stylish.ui.home

import android.util.Log
import com.guanhong.stylish.api.DataResourceCallback
import com.guanhong.stylish.repository.HotsRepository
import javax.inject.Inject

class HomeFragmentPresenter @Inject constructor(
        private val repository: HotsRepository,
        private val view: HomeFragmentContract.View)
    : HomeFragmentContract.Presenter {

    override fun getMarketingHots() {
       val hotsResponse =  repository.getMarketingHots(object : DataResourceCallback.getMarketingHots{
           override fun onSuccess(s: String) {
               Log.d("Huang", " aaaa " + s)
           }

           override fun onFail() {
               TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
           }
       })

        if (hotsResponse != null) {
//            view.onBindMarketingHots(hotsResponse)
        }
    }
}