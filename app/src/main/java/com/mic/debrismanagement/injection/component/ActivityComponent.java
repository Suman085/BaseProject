package com.mic.debrismanagement.injection.component;

import com.mic.debrismanagement.ui.activities.LoginActivity;
import com.mic.debrismanagement.ui.activities.MainActivity;
import com.mic.debrismanagement.injection.PerActivity;
import com.mic.debrismanagement.injection.module.ActivityModule;

import org.jetbrains.annotations.NotNull;

import dagger.Component;

/**
 * @author ishan
 * @since 08/07/16
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {


    void inject(MainActivity mainActivity);

    void inject(@NotNull LoginActivity loginActivity);
}
