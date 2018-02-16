package com.mic.rims.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.mic.rims.MyApplication;
import com.mic.rims.api.services.AuthenticationService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Suman on 2/15/2018.
 */
public class BaseApiManager {

    private static MyApplication.BaseURL baseUrl = new MyApplication.BaseURL();
    private static final String BASE_URL = baseUrl.getUrl();

    private static Retrofit retrofit;
    private static AuthenticationService authenticationApi;

    public BaseApiManager() {
        String authToken = "";
        createService(authToken);
    }

    private static void init() {
        authenticationApi = createApi(AuthenticationService.class);
//        clientsApi = createApi(ClientService.class);
//        savingAccountsListApi = createApi(SavingAccountsListService.class);


    }

    private static <T> T createApi(Class<T> clazz) {
        return retrofit.create(clazz);
    }

    public static void createService(String authToken) {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(new MyOkHttpClient(authToken).getOkHttpClient())
                .build();
        init();
    }

    public AuthenticationService getAuthenticationApi() {
        return authenticationApi;
    }
}
