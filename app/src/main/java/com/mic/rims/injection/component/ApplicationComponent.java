package com.mic.rims.injection.component;

import android.app.Application;
import android.content.Context;


import com.mic.rims.api.BaseApiManager;
import com.mic.rims.api.DataManager;
import com.mic.rims.injection.ApplicationContext;
import com.mic.rims.injection.module.ApplicationModule;
import com.mic.rims.persistence.DatabaseHelper;
import com.mic.rims.persistence.PreferencesHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author ishan
 * @since 08/07/16
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();
    DataManager dataManager();
    PreferencesHelper prefManager();
    BaseApiManager baseApiManager();
    DatabaseHelper databaseHelper();

}
