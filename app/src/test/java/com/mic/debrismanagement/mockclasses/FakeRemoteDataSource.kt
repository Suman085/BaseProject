package com.mic.debrismanagement.mockclasses

import com.google.gson.reflect.TypeToken
import com.mic.debrismanagement.mvp.model.User

/**
 * Created by Suman on 2/16/2018.
 */
class FakeRemoteDataSource {


    companion object {
        private val mTestDataFactory = TestDataFactory()

        fun getUser()= mTestDataFactory.getListTypePojo(object : TypeToken<User>() {

        }, FakeJsonName.USER_JSON)
    }
}