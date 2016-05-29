package com.travel.daily.traveldaily.bill;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.travel.daily.traveldaily.account.AccountManager;
import com.travel.daily.traveldaily.BaseActivity;
import com.travel.daily.traveldaily.database.DataHelper;
import com.travel.daily.traveldaily.R;
import com.travel.daily.traveldaily.ToolUtils;
import com.travel.daily.traveldaily.account.LoginActivity;
import com.travel.daily.traveldaily.database.dao.AccountBean;
import com.travel.daily.traveldaily.database.dao.BillBean;
import com.travel.daily.traveldaily.database.dao.DelicacyBean;
import com.travel.daily.traveldaily.database.dao.HotelBean;
import com.travel.daily.traveldaily.database.dao.SceneryBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 16/5/23.
 */
public class BillCreateActivity extends BaseActivity implements View.OnClickListener{

    public static final int TYPE_SCENERY = 0;
    public static final int TYPE_HOTEL = 1;
    public static final int TYPE_DELICACY = 2;

    ImageView backdrop;
    CollapsingToolbarLayout toolbar;
    TextView price;
    ImageView img;
    TextInputEditText detail;
    TextInputEditText name;
    FloatingActionButton add;
    BillBean bean;
    int type = -1;
    long id;
    SceneryBean sceneryBean;
    HotelBean hotelBean;
    DelicacyBean delicacyBean;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_create);

        if (getIntent() == null) {
            return;
        }
        id = getIntent().getLongExtra("id", -1);
        type = getIntent().getIntExtra("type", -1);

        backdrop = (ImageView) findViewById(R.id.backdrop);
        toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        img = (ImageView) findViewById(R.id.img);
        price = (TextView) findViewById(R.id.price);
        detail = (TextInputEditText) findViewById(R.id.detail);
        name = (TextInputEditText) findViewById(R.id.name);
        add = (FloatingActionButton) findViewById(R.id.add);
        add.setOnClickListener(this);

        loadData();
        showData();
    }

    private void loadData() {
        switch (type) {
            case TYPE_SCENERY:
                sceneryBean = DataHelper.loadScenery(this, id);
                break;
            case TYPE_HOTEL:
                hotelBean = DataHelper.loadHotel(this, id);
                break;
            case TYPE_DELICACY:
                delicacyBean = DataHelper.loadDelicacy(this, id);
                break;
            default:
                break;
        }
    }

    private void showData() {
        String imgUrl;
        String title;
        float price;
        switch (type) {
            case TYPE_SCENERY:
                imgUrl = sceneryBean.getImgUrl();
                title = sceneryBean.getTitle();
                price = sceneryBean.getPrice();
                break;
            case TYPE_HOTEL:
                imgUrl = hotelBean.getImgUrl();
                title = hotelBean.getTitle();
                price = hotelBean.getPrice();
                break;
            case TYPE_DELICACY:
                imgUrl = delicacyBean.getImgUrl();
                title = delicacyBean.getTitle();
                price = delicacyBean.getPrice();
                break;
            default:
                return;
        }
        backdrop.setImageBitmap(ToolUtils.getBitmapFromUrl(this, imgUrl));
        toolbar.setTitle(title);
        this.price.setText(getString(R.string.show_price, String.valueOf(price)));
        img.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ToolUtils.SELECT_PICTURE) {
            img.setImageBitmap(ToolUtils.getBitmapFromIntent(this, data));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img:
                ToolUtils.getPicFromGallery(this);
                break;
            case R.id.add:
                if (TextUtils.isEmpty(name.getText())) {
                    Toast.makeText(this, "请输入姓名~", Toast.LENGTH_SHORT).show();
                    return;
                }

                final AccountBean accountBean = AccountManager.getInstance(BillCreateActivity.this).getBean();
                if (accountBean == null) {
                    Toast.makeText(BillCreateActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BillCreateActivity.this, LoginActivity.class));
                    return;
                }

                new AlertDialog.Builder(this).setTitle("提示")
                        .setMessage("确认添加新账单吗？")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String detailStr = TextUtils.isEmpty(detail.getText()) ? "暂无描述" : detail.getText().toString();
                                String imgUrl;
                                float price;
                                switch (type) {
                                    case TYPE_SCENERY:
                                        imgUrl = sceneryBean.getImgUrl();
                                        price = sceneryBean.getPrice();
                                        break;
                                    case TYPE_HOTEL:
                                        imgUrl = hotelBean.getImgUrl();
                                        price = sceneryBean.getPrice();
                                        break;
                                    case TYPE_DELICACY:
                                        imgUrl = delicacyBean.getImgUrl();
                                        price = delicacyBean.getPrice();
                                        break;
                                    default:
                                        return;
                                }
                                bean = new BillBean();
                                bean.setImgUrl(ToolUtils.saveBitmap(BillCreateActivity.this, img));
                                bean.setTime(System.currentTimeMillis());
                                bean.setPrice(price);
                                bean.setDetail(detailStr);
                                bean.setName(name.getText().toString());
                                bean.setBgUrl(imgUrl);
                                bean.setId(System.currentTimeMillis());
                                DataHelper.insertBill(BillCreateActivity.this, bean);

                                long id = accountBean.getId();
                                List<BillBean> list = DataHelper.loadBillList(BillCreateActivity.this, id);
                                if (list == null) {
                                    list = new ArrayList<>();
                                }
                                list.add(bean);
                                DataHelper.insertBillList(BillCreateActivity.this, list, id);

                                Toast.makeText(BillCreateActivity.this, "账单已添加~", Toast.LENGTH_SHORT).show();
                                onBackPressed();
                            }
                        }).show();
                break;
            default:
                break;
        }
    }
}
