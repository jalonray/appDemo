package com.travel.daily.traveldaily;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.travel.daily.traveldaily.database.dao.ImageBean;
import com.travel.daily.traveldaily.database.dao.ImageBeanDao;
import com.travel.daily.traveldaily.database.DBHelper;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created on 16/5/23.
 */
public class ToolUtils {

    public static final int SELECT_PICTURE = 123;

    static public Bitmap getBitmapFromUrl(Context context, String url) {
        ImageBean image = DBHelper.getInstance(context).getImageDao().load(url);
        if (image == null || image.getPic() == null) {
            return null;
        }
        return BitmapFactory.decodeByteArray(
                image.getPic(),
                0,
                image.getPic().length
        );
    }

    static public String saveBitmap(Context context, ImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ImageBeanDao dao = DBHelper.getInstance(context).getImageDao();
        ImageBean bean = new ImageBean();
        String key = String.valueOf(System.currentTimeMillis());
        bean.setImgUrl(key);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        bean.setPic(stream.toByteArray());
        dao.insert(bean);
        return key;
    }

    static public String getTimeString(long million) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        Date currentTime = new Date(million);
        return formatter.format(currentTime);
    }

    static public void getPicFromGallery(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        activity.startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
    }

    static public void getPicFromGallery(Fragment fragment) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        fragment.startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
    }

    static public Bitmap getBitmapFromIntent(Context context, Intent data) {
        if (data == null) {
            return null;
        }
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;
            return BitmapFactory.decodeStream(context.getContentResolver().openInputStream(data.getData()), null, options);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
