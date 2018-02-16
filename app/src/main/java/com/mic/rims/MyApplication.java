package com.mic.rims;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;

import com.mic.rims.injection.component.ApplicationComponent;
import com.mic.rims.injection.component.DaggerApplicationComponent;
import com.mic.rims.injection.module.ApplicationModule;
import com.mic.rims.utils.LanguageHelper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Suman on 2/15/2018.
 */

public class MyApplication extends Application {

    ApplicationComponent applicationComponent;

    private static MyApplication instance;

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public static Context getContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
         }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LanguageHelper.onAttach(base, "en"));
    }

    public ApplicationComponent component() {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return applicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }

    /**
     * @author Vishwajeet
     * @since 09/06/16
     */

    public static class BaseURL {

        public static final String API_ENDPOINT = "floodrelief.southeastasia.cloudapp.azure.com/";
        public static final String PROTOCOL_HTTPS = "http://";

        private String url;

        public String getUrl() {
            if (url == null) {
                return PROTOCOL_HTTPS + API_ENDPOINT;
            }
            return url;
        }
    }

    /**
     * @author Vishwajeet
     * @since 21/06/16
     */
    public static class RimsInterceptor implements Interceptor {

        public static final String HEADER_AUTH = "Authorization";
        private String authToken;

        public RimsInterceptor(String authToken) {
            this.authToken = authToken;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request chainRequest = chain.request();
            Request.Builder builder = chainRequest.newBuilder();

            if (!TextUtils.isEmpty(authToken)) {
                builder.header(HEADER_AUTH, authToken);
            }

            Request request = builder.build();
            return chain.proceed(request);
        }
    }
}
