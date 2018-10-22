package com.mic.debrismanagement.api

import com.mic.debrismanagement.mvp.model.User
import com.mic.debrismanagement.persistence.DatabaseHelper
import com.mic.debrismanagement.persistence.PreferencesHelper

import javax.inject.Inject
import javax.inject.Singleton

import io.reactivex.Observable


/**
 * Created by Suman on 2/15/2018.
 */

@Singleton
class DataManager @Inject
constructor(private val preferencesHelper: PreferencesHelper, private val baseApiManager: BaseApiManager,
            private val databaseHelper: DatabaseHelper)//        clientId = this.preferencesHelper.getClientId();
{

    fun login(username: String, password: String): Observable<User> {
        return baseApiManager.getAuthenticationApi()!!.authenticate(username, password)
    }

}
