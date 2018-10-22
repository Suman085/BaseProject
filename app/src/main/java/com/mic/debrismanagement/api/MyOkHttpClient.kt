package com.mic.debrismanagement.api

import com.mic.debrismanagement.MyApplication

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by Suman on 2/15/2018.
 */

class MyOkHttpClient(private val authToken: String) {

    //Enable Full Body Logging
    //Setting Timeout 30 Seconds
    //Interceptor :> Full Body Logger and ApiRequest Header
    val okHttpClient: OkHttpClient
        get() {

            val builder = OkHttpClient.Builder()
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY
            builder.connectTimeout(60, TimeUnit.SECONDS)
            builder.readTimeout(60, TimeUnit.SECONDS)
            builder.addInterceptor(logger)
            builder.addInterceptor(MyApplication.RimsInterceptor(authToken))

            return builder.build()

        }
}