package com.mic.debrismanagement.injection.module;

import android.app.Application;
import android.content.Context;


import com.mic.debrismanagement.api.BaseApiManager;
import com.mic.debrismanagement.injection.ApplicationContext;
import com.mic.debrismanagement.persistence.DatabaseHelper;
import com.mic.debrismanagement.persistence.PreferencesHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author ishan
 * @since 08/07/16
 */
@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    PreferencesHelper providePrefManager(@ApplicationContext Context context) {
        return new PreferencesHelper(context);
    }

    @Provides
    @Singleton
    BaseApiManager provideBaseApiManager() {
        return new BaseApiManager();
    }

    @Provides
    @Singleton
    DatabaseHelper provideDatabaseHelper() {
        return new DatabaseHelper();
    }


}
