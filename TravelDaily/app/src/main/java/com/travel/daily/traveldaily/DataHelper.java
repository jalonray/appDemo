package com.travel.daily.traveldaily;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.travel.daily.traveldaily.dao.AccountBean;
import com.travel.daily.traveldaily.dao.BillBean;
import com.travel.daily.traveldaily.dao.BillList;
import com.travel.daily.traveldaily.dao.DelicacyBean;
import com.travel.daily.traveldaily.dao.DetailImage;
import com.travel.daily.traveldaily.dao.HotelBean;
import com.travel.daily.traveldaily.dao.ImageBean;
import com.travel.daily.traveldaily.dao.SceneryBean;

import java.util.List;

/**
 * Created on 2016/5/27.
 */

public class DataHelper {
    public static BillBean loadBill(Context context, long id) {
        return DBHelper.getInstance(context).getBillDao().load(id);
    }

    public static void insertBill(Context context, BillBean bean) {
        DBHelper.getInstance(context).getBillDao().insert(bean);
    }

    public static void removeBill(Context context, long id) {
        DBHelper.getInstance(context).getBillDao().deleteByKey(id);
    }

    public static void insertScenery(Context context, SceneryBean bean) {
        DBHelper.getInstance(context).getSceneryDao().insert(bean);
    }

    public static SceneryBean loadScenery(Context context, long id) {
        return DBHelper.getInstance(context).getSceneryDao().load(id);
    }

    public static void removeScenery(Context context, long id) {
        DBHelper.getInstance(context).getSceneryDao().deleteByKey(id);
    }

    public static void insertHotel(Context context, HotelBean bean) {
        DBHelper.getInstance(context).getHotelDao().insert(bean);
    }

    public static HotelBean loadHotel(Context context, long id) {
        return DBHelper.getInstance(context).getHotelDao().load(id);
    }

    public static void removeHotel(Context context, long id) {
        DBHelper.getInstance(context).getHotelDao().deleteByKey(id);
    }

    public static void insertDelicacy(Context context, DelicacyBean bean) {
        DBHelper.getInstance(context).getDelicacyDao().insert(bean);
    }

    public static DelicacyBean loadDelicacy(Context context, long id) {
        return DBHelper.getInstance(context).getDelicacyDao().load(id);
    }

    public static void removeDelicacy(Context context, long id) {
        DBHelper.getInstance(context).getDelicacyDao().deleteByKey(id);
    }

    public static DetailImage loadDetailImage(Context context, long id) {
        return DBHelper.getInstance(context).getDetailImageDao().load(id);
    }

    public static void insertDetailImage(Context context, DetailImage detailImage) {
        DBHelper.getInstance(context).getDetailImageDao().insert(detailImage);
    }

    public static void removeDetailImage(Context context, long id) {
        DBHelper.getInstance(context).getDetailImageDao().deleteByKey(id);
    }

    public static List<BillBean> loadBillList(Context context, long id) {
        BillList billList = DBHelper.getInstance(context).getBillListDao().load(id);
        if (billList == null) {
            return null;
        }
        return new Gson().fromJson(billList.getBillList(), new TypeToken<List<BillBean>>(){}.getType());
    }

    public static void insertBillList(Context context, List<BillBean> list, long id) {
        if (list == null) {
            return;
        }
        BillList billList = new BillList();
        billList.setId(id);
        billList.setBillList(encodeBillList(list));
        DBHelper.getInstance(context).getBillListDao().update(billList);
    }

    public static List<BillBean> decodeBillList(String jsonStr) {
        return new Gson().fromJson(jsonStr, new TypeToken<List<BillBean>>(){}.getType());
    }

    public static String encodeBillList(List<BillBean> list) {
        return new Gson().toJson(list);
    }

    public static List<AccountBean> getAllAccounts(Context context) {
        return DBHelper.getInstance(context).getAccountDao().loadAll();
    }

    public static AccountBean loadAccount(Context context, long key) {
        return DBHelper.getInstance(context).getAccountDao().load(key);
    }

    public static void insertAccount(Context context, AccountBean bean) {
        DBHelper.getInstance(context).getAccountDao().insert(bean);
    }

    public static ImageBean loadImage(Context context, String imgUrl) {
        return DBHelper.getInstance(context).getImageDao().load(imgUrl);
    }

    public static void removeImage(Context context, String imgUrl) {
        DBHelper.getInstance(context).getImageDao().deleteByKey(imgUrl);
    }
}
