package com.travel.daily.traveldaily.delicacy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.travel.daily.traveldaily.BaseActivity;
import com.travel.daily.traveldaily.database.DataHelper;
import com.travel.daily.traveldaily.R;
import com.travel.daily.traveldaily.ToolUtils;
import com.travel.daily.traveldaily.bill.BillCreateActivity;
import com.travel.daily.traveldaily.database.dao.DelicacyBean;
import com.travel.daily.traveldaily.database.dao.DetailImage;

/**
 * Created on 16/5/19.
 */

public class DelicacyDetailActivity extends BaseActivity implements View.OnClickListener {

    DelicacyBean bean;
    ImageView backdrop;
    CollapsingToolbarLayout toolbar;
    TextView title;
    TextView detail;
    FloatingActionButton add;
    TextView price;
    LinearLayout container;
    long id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        id = getIntent().getLongExtra("id", -1);
        backdrop = (ImageView) findViewById(R.id.backdrop);
        toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        title = (TextView) findViewById(R.id.title);
        detail = (TextView) findViewById(R.id.detail);
        price = (TextView) findViewById(R.id.price);
        container = (LinearLayout) findViewById(R.id.container);
        add = (FloatingActionButton) findViewById(R.id.add);
        add.setOnClickListener(this);

        loadData();
        showData();
    }

    private void loadData() {
        bean = DataHelper.loadDelicacy(this, id);
    }

    private void showData() {
        if (bean == null) {
            return;
        }
        backdrop.setImageBitmap(ToolUtils.getBitmapFromUrl(this, bean.getImgUrl()));
        title.setText(bean.getTitle());
        toolbar.setTitle(bean.getTitle());
        detail.setText(bean.getDetail());
        price.setText(getString(R.string.show_price, String.valueOf(bean.getPrice())));
        showImages();
    }

    private void showImages() {
        DetailImage images = DataHelper.loadDetailImage(this, id);
        if (images == null) {
            return;
        }
        for (int i = 0; i < 3; i++) {
            String imgUrl = null;
            if (i == 0) {
                imgUrl = images.getPic0();
            } else if (i == 1) {
                imgUrl = images.getPic1();
            } else if (i == 2) {
                imgUrl = images.getPic2();
            }
            if (imgUrl == null) {
                continue;
            }
            CardView view = (CardView) LayoutInflater.from(this).inflate(R.layout.card_detail_image, container, false);
            ((ImageView) view.findViewById(R.id.img)).setImageBitmap(ToolUtils.getBitmapFromUrl(this, imgUrl));
            ((TextView) view.findViewById(R.id.describe)).setText(bean.getTitle());
            container.addView(view);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                if (bean == null) {
                    return;
                }
                Intent intent = new Intent(this, BillCreateActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("type", BillCreateActivity.TYPE_DELICACY);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
