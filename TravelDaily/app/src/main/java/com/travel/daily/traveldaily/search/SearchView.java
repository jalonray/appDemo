package com.travel.daily.traveldaily.search;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.travel.daily.traveldaily.R;
import com.travel.daily.traveldaily.ToolUtils;
import com.travel.daily.traveldaily.bill.BillCreateActivity;
import com.travel.daily.traveldaily.database.dao.DelicacyBean;
import com.travel.daily.traveldaily.database.dao.HotelBean;
import com.travel.daily.traveldaily.database.dao.SceneryBean;
import com.travel.daily.traveldaily.delicacy.DelicacyDetailActivity;
import com.travel.daily.traveldaily.hotel.HotelDetailActivity;
import com.travel.daily.traveldaily.mvp.views.IView;
import com.travel.daily.traveldaily.scenery.SceneryDetailActivity;

/**
 * Created on 2016/6/5.
 */

public class SearchView extends IView<Object>{
    private Object data;
    Holder holder;
    public SearchView(Context context) {
        super(context);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context) {
        inflate(context, R.layout.card_list_layout, this);
        holder = new Holder();
        holder.img = (ImageView) findViewById(R.id.img);
        holder.title = (TextView) findViewById(R.id.title);
        holder.detail = (TextView) findViewById(R.id.detail);
        holder.action = (TextView) findViewById(R.id.action);
        holder.price = (TextView) findViewById(R.id.price);
        holder.container = (RelativeLayout) findViewById(R.id.container);
    }

    @Override
    public void setData(Object data) {
        this.data = data;
        if (data instanceof SceneryBean) {
            SceneryBean bean = (SceneryBean) data;
            holder.img.setImageBitmap(ToolUtils.getBitmapFromUrl(getContext(), bean.getImgUrl()));
            holder.title.setText(bean.getTitle());
            holder.detail.setText(bean.getDetail());
            holder.price.setText(getContext().getString(R.string.show_price, String.valueOf(bean.getPrice())));
        } else if (data instanceof HotelBean) {
            HotelBean bean = (HotelBean) data;
            holder.img.setImageBitmap(ToolUtils.getBitmapFromUrl(getContext(), bean.getImgUrl()));
            holder.title.setText(bean.getTitle());
            holder.detail.setText(bean.getDetail());
            holder.price.setText(getContext().getString(R.string.show_price, String.valueOf(bean.getPrice())));
        } else if (data instanceof DelicacyBean) {
            DelicacyBean bean = (DelicacyBean) data;
            holder.img.setImageBitmap(ToolUtils.getBitmapFromUrl(getContext(), bean.getImgUrl()));
            holder.title.setText(bean.getTitle());
            holder.detail.setText(bean.getDetail());
            holder.price.setText(getContext().getString(R.string.show_price, String.valueOf(bean.getPrice())));
        }
        holder.action.setOnClickListener(this);
        holder.container.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        long id = -1;
        int type = -1;
        Class<?> cls = null;
        if (data instanceof SceneryBean) {
            id = ((SceneryBean)data).getId();
            type = BillCreateActivity.TYPE_SCENERY;
            cls = SceneryDetailActivity.class;
        } else if (data instanceof HotelBean) {
            id = ((HotelBean)data).getId();
            type = BillCreateActivity.TYPE_HOTEL;
            cls = HotelDetailActivity.class;
        } else if (data instanceof DelicacyBean) {
            id = ((DelicacyBean)data).getId();
            type = BillCreateActivity.TYPE_DELICACY;
            cls = DelicacyDetailActivity.class;
        }

        switch (v.getId()) {
            case R.id.container:
                Intent detail = new Intent(getContext(), cls);
                detail.putExtra("id", id);
                getContext().startActivity(detail);
                break;
            case R.id.action:
                Intent creator = new Intent(getContext(), BillCreateActivity.class);
                creator.putExtra("id", id);
                creator.putExtra("type", type);
                getContext().startActivity(creator);
                break;
            default:
                break;
        }
    }

    public class Holder {
        ImageView img;
        TextView title;
        TextView detail;
        TextView action;
        TextView price;
        RelativeLayout container;
    }
}
