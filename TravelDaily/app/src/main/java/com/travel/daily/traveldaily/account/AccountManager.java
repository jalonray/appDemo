package com.travel.daily.traveldaily.account;

import android.content.Context;
import android.content.SharedPreferences;

import com.travel.daily.traveldaily.database.DataHelper;
import com.travel.daily.traveldaily.database.dao.AccountBean;

/**
 * Created on 2016/5/29.
 */

public class AccountManager {
    private static final String KEY = "ACCOUNT";
    static private AccountManager manager;
    private AccountBean bean;
    private SharedPreferences preferences;
    public static AccountManager getInstance(Context context) {
        if (manager == null) {
            manager = new AccountManager();
            manager.preferences = context.getApplicationContext().getSharedPreferences(KEY, Context.MODE_APPEND);
            long id = manager.preferences.getLong(KEY, -1);
            manager.bean = DataHelper.loadAccount(context, id);
        }
        return manager;
    }

    private AccountManager() {
    }

    public AccountBean getBean() {
        return bean;
    }

    public void setBean(AccountBean bean) {
        this.bean = bean;
        long id = bean == null ? -1 : bean.getId();
        preferences.edit().putLong(KEY, id).apply();
    }

    public void clear() {
        setBean(null);
    }
}
