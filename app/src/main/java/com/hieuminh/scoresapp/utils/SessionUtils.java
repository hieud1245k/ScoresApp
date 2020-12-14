package com.hieuminh.scoresapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SessionUtils {

    private SharedPreferences prefs;

    public SessionUtils(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setUserId(String userId) {
        prefs.edit().putString("userId", userId).commit();
    }

    public String getUserId() {
        String userId = prefs.getString("userId","");
        return userId;
    }
}