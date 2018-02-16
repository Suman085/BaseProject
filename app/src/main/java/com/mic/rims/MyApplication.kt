package com.mic.rims

import android.app.Application
import android.content.Context
import android.support.v7.app.AppCompatDelegate
import android.text.TextUtils

import com.mic.rims.injection.component.ApplicationComponent
import com.mic.rims.injection.component.DaggerApplicationComponent
import com.mic.rims.injection.module.ApplicationModule
import com.mic.rims.utils.LanguageHelper

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by Suman on 2/15/2018.
 */

class MyApplication : Application() {

    internal var applicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LanguageHelper.onAttach(base, "en"))
    }

    fun component(): ApplicationComponent {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(ApplicationModule(this))
                    .build()
        }
        return applicationComponent!!
    }

    // Needed to replace the component with a test specific one
    fun setComponent(applicationComponent: ApplicationComponent) {
        this.applicationComponent = applicationComponent
    }

    /**
     * @author Vishwajeet
     * @since 09/06/16
     */

    class BaseURL {

        private val url: String? = null

        fun getUrl(): String {
            return url ?: PROTOCOL_HTTPS + API_ENDPOINT
        }

        companion object {

            val API_ENDPOINT = "floodrelief.southeastasia.cloudapp.azure.com/"
            val PROTOCOL_HTTPS = "http://"
        }
    }

    /**
     * @author Vishwajeet
     * @since 21/06/16
     */
    class RimsInterceptor(private val authToken: String) : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val chainRequest = chain.request()
            val builder = chainRequest.newBuilder()

            if (!TextUtils.isEmpty(authToken)) {
                builder.header(HEADER_AUTH, authToken)
            }

            val request = builder.build()
            return chain.proceed(request)
        }

        companion object {

            val HEADER_AUTH = "Authorization"
        }
    }

    companion object {

        private var instance: MyApplication? = null

        operator fun get(context: Context): MyApplication {
            return context.applicationContext as MyApplication
        }

        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }

        val context: Context?
            get() = instance
    }
}
