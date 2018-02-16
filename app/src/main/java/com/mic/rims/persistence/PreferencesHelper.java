package com.mic.rims.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.mic.rims.R;
import com.mic.rims.injection.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Vishwajeet
 * @since 07/06/16
 */

@Singleton
public class PreferencesHelper {
    private static final String USER_ID = "preferences_user_id";
    private static final String TOKEN = "preferences_token";
    private static final String USER_NAME = "preferences_user_name";
    private static final String IS_FIRST_TIME = "preferences_is_first_time";
    private Context context;
    private SharedPreferences sharedPreferences;

    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.context=context;
    }

    public void clear() {
        String preservedLang=preserveSelectedLanguage();
        sharedPreferences.edit().clear().apply();
        setThePreservedLanguage(preservedLang);

    }
    public String preserveSelectedLanguage(){
        return sharedPreferences.getString(context.getString(R.string.language_type),"");
    }
    public void setThePreservedLanguage(String lang){
        sharedPreferences.edit().putString(context.getString(R.string.language_type),lang).apply();
    }

    public int getInt(String preferenceKey, int preferenceDefaultValue) {
        return sharedPreferences.getInt(preferenceKey, preferenceDefaultValue);
    }

    public void putInt(String preferenceKey, int preferenceValue) {
        sharedPreferences.edit().putInt(preferenceKey, preferenceValue).apply();
    }

    public long getLong(String preferenceKey, long preferenceDefaultValue) {
        return sharedPreferences.getLong(preferenceKey, preferenceDefaultValue);
    }

    public void putLong(String preferenceKey, long preferenceValue) {
        sharedPreferences.edit().putLong(preferenceKey, preferenceValue).apply();
    }

    public String getString(String preferenceKey, String preferenceDefaultValue) {
        return sharedPreferences.getString(preferenceKey, preferenceDefaultValue);
    }

    public void putString(String preferenceKey, String preferenceValue) {
        sharedPreferences.edit().putString(preferenceKey, preferenceValue).apply();
    }

    public void putBoolean(String preferenceKey, boolean preferenceValue) {
        sharedPreferences.edit().putBoolean(preferenceKey, preferenceValue).apply();
    }

    public boolean getBoolean(String preferenceKey, boolean preferenceDefaultValue) {
        return sharedPreferences.getBoolean(preferenceKey, preferenceDefaultValue);
    }

    public void saveToken(String token) {
        putString(TOKEN, token);
    }

    public void clearToken() {
        putString(TOKEN, "");
    }

    public String getToken() {
        return getString(TOKEN, "");
    }

    public long getUserId() {
        return getLong(USER_ID, -1);
    }

    public void setUserId(long id) {
        putLong(USER_ID, id);
    }


    public String getUserName() {
        return getString(USER_NAME, "");
    }

    public void setUserName(String userName) {
        putString(USER_NAME, userName);
    }

    public void setIsFirstTime() {
        putBoolean(IS_FIRST_TIME, false);
    }

    public boolean getIsFirstTime() {
        return getBoolean(IS_FIRST_TIME,true);
    }
}
