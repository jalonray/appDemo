package com.travel.daily.traveldaily;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.travel.daily.traveldaily.dao.ImageBean;

/**
 * Created on 16/5/23.
 */
public class ImageUtils {

    static public Bitmap getBitmapFromUrl(Context context, String url) {
        ImageBean image = DBHelper.getInstance(context).getImageDao().load(url);
        return BitmapFactory.decodeByteArray(
                image.getPic(),
                0,
                image.getPic().length
        );
    }
}
