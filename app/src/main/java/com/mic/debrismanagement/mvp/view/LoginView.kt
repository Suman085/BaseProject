package com.mic.debrismanagement.mvp.view

import com.mic.debrismanagement.mvp.MVPView
import com.mic.debrismanagement.mvp.model.User

/**
 * Created by Suman on 2/15/2018.
 */
interface LoginView : MVPView {
    fun showUsernameError(string: String?,field:String?)
    fun showPasswordError(string: String?,field: String?)
    fun onSuccessLogin(user: User)
    fun onFailureLogin(message: String?)
    fun gotoMainActivity()
}