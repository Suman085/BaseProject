package com.mic.rims.api

import com.mic.rims.MyApplication

import java.security.KeyStore
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.Arrays
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

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