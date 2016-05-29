package com.travel.daily.traveldaily.database;

import android.content.Context;

import com.travel.daily.traveldaily.database.dao.AccountBeanDao;
import com.travel.daily.traveldaily.database.dao.BillBeanDao;
import com.travel.daily.traveldaily.database.dao.BillListDao;
import com.travel.daily.traveldaily.database.dao.DaoMaster;
import com.travel.daily.traveldaily.database.dao.DaoSession;
import com.travel.daily.traveldaily.database.dao.DelicacyBeanDao;
import com.travel.daily.traveldaily.database.dao.DetailImageDao;
import com.travel.daily.traveldaily.database.dao.HotelBeanDao;
import com.travel.daily.traveldaily.database.dao.ImageBeanDao;
import com.travel.daily.traveldaily.database.dao.SceneryBeanDao;

/**
 * Created on 16/5/27.
 */

public class DBHelper {
    private static DBHelper INSTANCE = null;

    /**
     * not thread-safe
     */
    public static DBHelper getInstance(Context context) {
        if(INSTANCE == null)
            INSTANCE = new DBHelper(context);
        return INSTANCE;
    }

    private static final String DB_NAME = "clustering.db";
    private DaoSession daoSession;

    private DBHelper(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);

        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());

        daoSession = daoMaster.newSession();
    }

    public SceneryBeanDao getSceneryDao() {
        return daoSession.getSceneryBeanDao();
    }

    public HotelBeanDao getHotelDao() {
        return daoSession.getHotelBeanDao();
    }

    public DelicacyBeanDao getDelicacyDao() {
        return daoSession.getDelicacyBeanDao();
    }

    public BillBeanDao getBillDao() {
        return daoSession.getBillBeanDao();
    }

    public BillListDao getBillListDao() {
        return daoSession.getBillListDao();
    }

    public ImageBeanDao getImageDao() {
        return daoSession.getImageBeanDao();
    }

    public DetailImageDao getDetailImageDao() {
        return daoSession.getDetailImageDao();
    }

    public AccountBeanDao getAccountDao() {
        return daoSession.getAccountBeanDao();
    }
}
