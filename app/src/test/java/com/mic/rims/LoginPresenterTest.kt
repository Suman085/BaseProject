package com.mic.rims

import android.content.Context
import com.mic.rims.api.DataManager
import com.mic.rims.mockclasses.FakeRemoteDataSource
import com.mic.rims.mvp.model.User
import com.mic.rims.mvp.presenter.LoginPresenter
import com.mic.rims.mvp.view.LoginView
import com.mic.rims.persistence.DatabaseHelper
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Suman on 2/16/2018.
 */
@RunWith(MockitoJUnitRunner::class)
class LoginPresenterTest {

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var dataManager: DataManager

    @Mock
    lateinit var databaseHelper:DatabaseHelper

    @Mock
    lateinit var loginView:LoginView

    lateinit var presenter:LoginPresenter
    private var user:User?=null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LoginPresenter(dataManager,databaseHelper,context)
        presenter.attachView(loginView)
        user = FakeRemoteDataSource.getUser()
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        presenter.detachView()
    }

    @Test
    fun testLogin() {
        `when`(dataManager.login("test3", "test3")).thenReturn(Observable.just<User>(user))
        presenter.login("test3","test3")

        verify<LoginView>(loginView).showProgress()
        verify<LoginView>(loginView).hideProgress()
        verify<LoginView>(loginView).onSuccessLogin(user!!)

    }

}