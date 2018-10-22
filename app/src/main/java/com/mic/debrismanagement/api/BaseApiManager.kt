package com.mic.debrismanagement.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.mic.debrismanagement.MyApplication
import com.mic.debrismanagement.api.services.AuthenticationService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Suman on 2/15/2018.
 */
class BaseApiManager {
    init {
        val authToken = ""
        createService(authToken)
    }

    fun getAuthenticationApi(): AuthenticationService? {
        return authenticationApi
    }

    companion object {

        private val baseUrl = MyApplication.BaseURL()
        private val BASE_URL = baseUrl.getUrl()

        private var retrofit: Retrofit? = null
        private var authenticationApi: AuthenticationService? = null

        private fun init() {
            authenticationApi = createApi(AuthenticationService::class.java)
            //        clientsApi = createApi(ClientService.class);
            //        savingAccountsListApi = createApi(SavingAccountsListService.class);


        }

        private fun <T> createApi(clazz: Class<T>): T {
            return retrofit!!.create(clazz)
        }

        fun createService(authToken: String) {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(MyOkHttpClient(authToken).okHttpClient)
                    .build()
            init()
        }
    }
}
