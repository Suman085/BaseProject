package com.mic.rims.api;

import com.mic.rims.MyApplication;

import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Suman on 2/15/2018.
 */

public class MyOkHttpClient {
    private String authToken;

    public MyOkHttpClient(String authToken) {
        this.authToken = authToken;
    }

    public OkHttpClient getOkHttpClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //Enable Full Body Logging
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);

        //Setting Timeout 30 Seconds
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);

        //Interceptor :> Full Body Logger and ApiRequest Header
        builder.addInterceptor(logger);
        builder.addInterceptor(new MyApplication.RimsInterceptor(authToken));

        return builder.build();

    }
}