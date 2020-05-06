package com.example.helloworld;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
        private static SharedPrefManager instance;
        private static Context ctx;

        private static final String SHARED_PREF_NAME = "mysharedpref12";
        private static final String KEY_USER_ID = "user_ID";
        private static final String KEY_USERNAME = "username";
        private SharedPrefManager(Context context) {
            ctx = context;
        }

        public static synchronized SharedPrefManager getInstance(Context context) {
            if (instance == null) {
                instance = new SharedPrefManager(context);
            }
            return instance;
        }

        public boolean userLogin(int user_id, String username){
            SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putInt(KEY_USER_ID, user_id);
            editor.putString(KEY_USERNAME, username);

            editor.apply();

            return true;
        }

        // Check if user is logged in
        public boolean isLoggedIn(){
            SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            if(sharedPreferences.getString(KEY_USERNAME, null) != null){
                return true;
            }
            return false;
        }

        // Logout
        public boolean logOut(){
            SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();

            return true;
        }
}
