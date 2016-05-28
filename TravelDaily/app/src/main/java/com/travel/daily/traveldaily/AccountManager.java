package com.travel.daily.traveldaily;

import android.content.Context;
import android.content.SharedPreferences;

import com.travel.daily.traveldaily.dao.AccountBean;

/**
 * Created on 2016/5/29.
 */

public class AccountManager {
    private static final String KEY = "ACCOUNT";
    static private AccountManager manager;
    private AccountBean bean;
    private SharedPreferences preferences;
    public static AccountManager getInstance(Context context) {
        if (manager != null) {
            manager =  new AccountManager(context);
        }
        return manager;
    }

    private AccountManager(Context context) {
        preferences = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
        long id = preferences.getLong(KEY, -1);
        bean = DataHelper.loadAccount(context, id);
    }

    public AccountBean getBean() {
        return bean;
    }

    public void setBean(AccountBean bean) {
        this.bean = bean;
        preferences.edit().putLong(KEY, bean.getId()).apply();
    }
}
