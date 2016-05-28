package com.travel.daily.traveldaily.debug;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.travel.daily.traveldaily.BaseActivity;
import com.travel.daily.traveldaily.DataHelper;
import com.travel.daily.traveldaily.R;
import com.travel.daily.traveldaily.ToolUtils;
import com.travel.daily.traveldaily.dao.DelicacyBean;
import com.travel.daily.traveldaily.dao.DetailImage;
import com.travel.daily.traveldaily.dao.HotelBean;
import com.travel.daily.traveldaily.dao.SceneryBean;

/**
 * Created on 16/5/27.
 */

public class DebugAddActivity extends BaseActivity implements View.OnClickListener{
    static final int SCENERY0 = 0;
    static final int SCENERY1 = 1;
    static final int SCENERY2 = 2;
    static final int SCENERY3 = 3;

    static final int HOTEL0 = 4;
    static final int HOTEL1 = 5;
    static final int HOTEL2 = 6;
    static final int HOTEL3 = 7;

    static final int DELICACY0 = 8;
    static final int DELICACY1 = 9;
    static final int DELICACY2 = 10;
    static final int DELICACY3 = 11;

    // Scenery
    TextInputEditText sceneryName;
    TextInputEditText sceneryDetail;
    TextInputEditText sceneryPrice;
    ImageView sceneryImg0;
    ImageView sceneryImg1;
    ImageView sceneryImg2;
    ImageView sceneryImg3;
    TextView addScenery;

    // Hotel
    TextInputEditText hotelName;
    TextInputEditText hotelDetail;
    TextInputEditText hotelPrice;
    ImageView hotelImg0;
    ImageView hotelImg1;
    ImageView hotelImg2;
    ImageView hotelImg3;
    TextView addHotel;

    // Delicacy
    TextInputEditText delicacyName;
    TextInputEditText delicacyDetail;
    TextInputEditText delicacyPrice;
    ImageView delicacyImg0;
    ImageView delicacyImg1;
    ImageView delicacyImg2;
    ImageView delicacyImg3;
    TextView addDelicacy;

    int type = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
        sceneryName = (TextInputEditText) findViewById(R.id.sceneryName);
        sceneryDetail = (TextInputEditText) findViewById(R.id.sceneryDetail);
        sceneryImg0 = (ImageView) findViewById(R.id.sceneryImg0);
        sceneryImg1 = (ImageView) findViewById(R.id.sceneryImg1);
        sceneryImg2 = (ImageView) findViewById(R.id.sceneryImg2);
        sceneryImg3 = (ImageView) findViewById(R.id.sceneryImg3);
        sceneryPrice = (TextInputEditText) findViewById(R.id.sceneryPrice);
        addScenery = (TextView) findViewById(R.id.addScenery);

        hotelName = (TextInputEditText) findViewById(R.id.hotelName);
        hotelDetail = (TextInputEditText) findViewById(R.id.hotelDetail);
        hotelImg0 = (ImageView) findViewById(R.id.hotelImg0);
        hotelImg1 = (ImageView) findViewById(R.id.hotelImg1);
        hotelImg2 = (ImageView) findViewById(R.id.hotelImg2);
        hotelImg3 = (ImageView) findViewById(R.id.hotelImg3);
        hotelPrice = (TextInputEditText) findViewById(R.id.hotelPrice);
        addHotel = (TextView) findViewById(R.id.addHotel);

        delicacyName = (TextInputEditText) findViewById(R.id.delicacyName);
        delicacyDetail = (TextInputEditText) findViewById(R.id.delicacyDetail);
        delicacyImg0 = (ImageView) findViewById(R.id.delicacyImg0);
        delicacyImg1 = (ImageView) findViewById(R.id.delicacyImg1);
        delicacyImg2 = (ImageView) findViewById(R.id.delicacyImg2);
        delicacyImg3 = (ImageView) findViewById(R.id.delicacyImg3);
        delicacyPrice = (TextInputEditText) findViewById(R.id.delicacyPrice);
        addDelicacy = (TextView) findViewById(R.id.addDelicacy);

        sceneryImg0.setOnClickListener(this);
        sceneryImg1.setOnClickListener(this);
        sceneryImg2.setOnClickListener(this);
        sceneryImg3.setOnClickListener(this);
        addScenery.setOnClickListener(this);

        hotelImg0.setOnClickListener(this);
        hotelImg1.setOnClickListener(this);
        hotelImg2.setOnClickListener(this);
        hotelImg3.setOnClickListener(this);
        addHotel.setOnClickListener(this);

        delicacyImg0.setOnClickListener(this);
        delicacyImg1.setOnClickListener(this);
        delicacyImg2.setOnClickListener(this);
        delicacyImg3.setOnClickListener(this);
        addDelicacy.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ToolUtils.SELECT_PICTURE) {
            switch (type) {
                case SCENERY0:
                    sceneryImg0.setImageBitmap(ToolUtils.getBitmapFromIntent(this, data));
                    break;
                case SCENERY1:
                    sceneryImg1.setImageBitmap(ToolUtils.getBitmapFromIntent(this, data));
                    break;
                case SCENERY2:
                    sceneryImg2.setImageBitmap(ToolUtils.getBitmapFromIntent(this, data));
                    break;
                case SCENERY3:
                    sceneryImg3.setImageBitmap(ToolUtils.getBitmapFromIntent(this, data));
                    break;
                case HOTEL0:
                    hotelImg0.setImageBitmap(ToolUtils.getBitmapFromIntent(this, data));
                    break;
                case HOTEL1:
                    hotelImg1.setImageBitmap(ToolUtils.getBitmapFromIntent(this, data));
                    break;
                case HOTEL2:
                    hotelImg2.setImageBitmap(ToolUtils.getBitmapFromIntent(this, data));
                    break;
                case HOTEL3:
                    hotelImg3.setImageBitmap(ToolUtils.getBitmapFromIntent(this, data));
                    break;
                case DELICACY0:
                    delicacyImg0.setImageBitmap(ToolUtils.getBitmapFromIntent(this, data));
                    break;
                case DELICACY1:
                    delicacyImg1.setImageBitmap(ToolUtils.getBitmapFromIntent(this, data));
                    break;
                case DELICACY2:
                    delicacyImg2.setImageBitmap(ToolUtils.getBitmapFromIntent(this, data));
                    break;
                case DELICACY3:
                    delicacyImg3.setImageBitmap(ToolUtils.getBitmapFromIntent(this, data));
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addScenery:
                float sceneryPrice = Float.parseFloat(this.sceneryPrice.getText().toString());
                if (checkPrice(sceneryPrice)) {
                    long sceneryId = SystemClock.currentThreadTimeMillis();
                    SceneryBean sceneryBean = new SceneryBean();
                    sceneryBean.setId(sceneryId);
                    sceneryBean.setTitle(sceneryName.getText().toString());
                    sceneryBean.setDetail(sceneryDetail.getText().toString());
                    sceneryBean.setPrice(sceneryPrice);
                    sceneryBean.setImgUrl(ToolUtils.saveBitmap(this, sceneryImg0.getDrawingCache()));
                    DataHelper.insertScenery(this, sceneryBean);

                    DetailImage sceneryImage = new DetailImage();
                    sceneryImage.setId(sceneryId);
                    sceneryImage.setPic0(ToolUtils.saveBitmap(this, sceneryImg1.getDrawingCache()));
                    sceneryImage.setPic0(ToolUtils.saveBitmap(this, sceneryImg2.getDrawingCache()));
                    sceneryImage.setPic0(ToolUtils.saveBitmap(this, sceneryImg3.getDrawingCache()));
                    DataHelper.insertDetailImage(this, sceneryImage);
                }
                break;
            case R.id.addHotel:
                float hotelPrice = Float.parseFloat(this.hotelPrice.getText().toString());
                if (checkPrice(hotelPrice)) {
                    long hotelId = SystemClock.currentThreadTimeMillis();
                    HotelBean hotelBean = new HotelBean();
                    hotelBean.setId(hotelId);
                    hotelBean.setTitle(hotelName.getText().toString());
                    hotelBean.setDetail(hotelDetail.getText().toString());
                    hotelBean.setPrice(hotelPrice);
                    hotelBean.setImgUrl(ToolUtils.saveBitmap(this, hotelImg0.getDrawingCache()));
                    DataHelper.insertHotel(this, hotelBean);

                    DetailImage hotelImage = new DetailImage();
                    hotelImage.setId(hotelId);
                    hotelImage.setPic0(ToolUtils.saveBitmap(this, hotelImg1.getDrawingCache()));
                    hotelImage.setPic0(ToolUtils.saveBitmap(this, hotelImg2.getDrawingCache()));
                    hotelImage.setPic0(ToolUtils.saveBitmap(this, hotelImg3.getDrawingCache()));
                    DataHelper.insertDetailImage(this, hotelImage);
                }
                break;
            case R.id.addDelicacy:
                float delicacyPrice = Float.parseFloat(this.delicacyPrice.getText().toString());
                if (checkPrice(delicacyPrice)) {
                    long delicacyId = SystemClock.currentThreadTimeMillis();
                    DelicacyBean delicacyBean = new DelicacyBean();
                    delicacyBean.setId(delicacyId);
                    delicacyBean.setTitle(delicacyName.getText().toString());
                    delicacyBean.setDetail(delicacyDetail.getText().toString());
                    delicacyBean.setPrice(delicacyPrice);
                    delicacyBean.setImgUrl(ToolUtils.saveBitmap(this, delicacyImg0.getDrawingCache()));
                    DataHelper.insertDelicacy(this, delicacyBean);

                    DetailImage delicacyImage = new DetailImage();
                    delicacyImage.setId(delicacyId);
                    delicacyImage.setPic0(ToolUtils.saveBitmap(this, delicacyImg1.getDrawingCache()));
                    delicacyImage.setPic0(ToolUtils.saveBitmap(this, delicacyImg2.getDrawingCache()));
                    delicacyImage.setPic0(ToolUtils.saveBitmap(this, delicacyImg3.getDrawingCache()));
                    DataHelper.insertDetailImage(this, delicacyImage);
                }
                break;
            case R.id.sceneryImg0:
                type = SCENERY0;
                ToolUtils.getPicFromGallery(this);
                break;
            case R.id.sceneryImg1:
                type = SCENERY1;
                ToolUtils.getPicFromGallery(this);
                break;
            case R.id.sceneryImg2:
                type = SCENERY2;
                ToolUtils.getPicFromGallery(this);
                break;
            case R.id.sceneryImg3:
                type = SCENERY3;
                ToolUtils.getPicFromGallery(this);
                break;
            case R.id.hotelImg0:
                type = HOTEL0;
                ToolUtils.getPicFromGallery(this);
                break;
            case R.id.hotelImg1:
                type = HOTEL1;
                ToolUtils.getPicFromGallery(this);
                break;
            case R.id.hotelImg2:
                type = HOTEL2;
                ToolUtils.getPicFromGallery(this);
                break;
            case R.id.hotelImg3:
                type = HOTEL3;
                ToolUtils.getPicFromGallery(this);
                break;
            case R.id.delicacyImg0:
                type = DELICACY0;
                ToolUtils.getPicFromGallery(this);
                break;
            case R.id.delicacyImg1:
                type = DELICACY1;
                ToolUtils.getPicFromGallery(this);
                break;
            case R.id.delicacyImg2:
                type = DELICACY2;
                ToolUtils.getPicFromGallery(this);
                break;
            case R.id.delicacyImg3:
                type = DELICACY3;
                ToolUtils.getPicFromGallery(this);
                break;
            default:
                break;
        }
    }

    private boolean checkPrice(float price) {
        if (price <= 0) {
            Toast.makeText(this, "价格有误", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
