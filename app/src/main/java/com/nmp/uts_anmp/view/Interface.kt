package com.nmp.uts_anmp.view

import android.view.View
import android.widget.CompoundButton
import com.nmp.uts_anmp.model.Hobby
import com.nmp.uts_anmp.model.User


interface UserLoginClick {
    fun onUserLoginClick(v: View)
    fun onUserUpdateClick(v: View)
    fun onUserLogoutClick(v: View)
}

interface ButtonDetailClickListener{
    fun onButtonDetailClick(v: View)
    fun onButtonUpdateClick(v: View)
}

