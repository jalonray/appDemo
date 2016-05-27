package com.travel.daily.traveldaily;

import android.content.Context;

import com.travel.daily.traveldaily.dao.BillBeanDao;
import com.travel.daily.traveldaily.dao.BillListDao;
import com.travel.daily.traveldaily.dao.DaoMaster;
import com.travel.daily.traveldaily.dao.DaoSession;
import com.travel.daily.traveldaily.dao.DelicacyBeanDao;
import com.travel.daily.traveldaily.dao.HotelBeanDao;
import com.travel.daily.traveldaily.dao.ImageBeanDao;
import com.travel.daily.traveldaily.dao.SceneryBeanDao;

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
}
