package com.guanhong.stylish.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.guanhong.stylish.BaseFragment
import com.guanhong.stylish.R
import com.guanhong.stylish.util.UserManager
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun newInstance(): ProfileFragment {
        return ProfileFragment()
    }

    fun setUserData() {

        activity!!.runOnUiThread{

            userName.text = UserManager.user.name

            Glide.with(context!!).load(UserManager.user.picture).into(circleImageView)
        }
        Log.d("Huang", " email " + UserManager.user.email)
        Log.d("Huang", " name " + UserManager.user.name)
        Log.d("Huang", " picture " + UserManager.user.picture)
    }
}