package com.mic.debrismanagement.injection.component;

import android.app.Application;
import android.content.Context;


import com.mic.debrismanagement.api.BaseApiManager;
import com.mic.debrismanagement.api.DataManager;
import com.mic.debrismanagement.injection.ApplicationContext;
import com.mic.debrismanagement.injection.module.ApplicationModule;
import com.mic.debrismanagement.persistence.DatabaseHelper;
import com.mic.debrismanagement.persistence.PreferencesHelper;

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
