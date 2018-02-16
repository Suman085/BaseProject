package com.mic.rims.api;

import com.mic.rims.mvp.model.User;
import com.mic.rims.persistence.DatabaseHelper;
import com.mic.rims.persistence.PreferencesHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


/**
 * Created by Suman on 2/15/2018.
 */

@Singleton
public class DataManager {

    private final PreferencesHelper preferencesHelper;
    private final BaseApiManager baseApiManager;
    private final DatabaseHelper databaseHelper;
    private long clientId;

    @Inject
    public DataManager(PreferencesHelper preferencesHelper, BaseApiManager baseApiManager,
                       DatabaseHelper databaseHelper) {
        this.preferencesHelper = preferencesHelper;
        this.baseApiManager = baseApiManager;
        this.databaseHelper = databaseHelper;
//        clientId = this.preferencesHelper.getClientId();
    }

    public Observable<User> login(String username, String password) {
        return baseApiManager.getAuthenticationApi().authenticate(username, password);
    }

}
