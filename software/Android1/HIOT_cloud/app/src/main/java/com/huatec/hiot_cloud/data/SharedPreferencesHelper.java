package com.huatec.hiot_cloud.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.huatec.hiot_cloud.injection.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * SharedPreferencesHelper类
 */
@Singleton
public class SharedPreferencesHelper {

    private static final String PREF_FILE_NAME = "userconfig";

    private static final String PREF_KEY_USER_TOKEN = "PREF_KEY_USER_TOKEN";

    private Context Context;
    private final SharedPreferences mPref;


    /**
     * 构造函数
     * @param context
     */
    @Inject
    public SharedPreferencesHelper(@ApplicationContext Context context) {
        Context = context;
        mPref = context.getSharedPreferences(PREF_FILE_NAME,Context.MODE_PRIVATE);
    }

    /**
     * 写入用户token
     * @param value
     */
    public void setuserToken(String value){
        mPref.edit().putString(PREF_FILE_NAME,value).apply();
    }


    /**
     * 写入用户token
     * @return
     */
    public String getUserToken(){
       return  mPref.getString(PREF_FILE_NAME,"");
    }

}
