package com.travel.daily.traveldaily.hotel;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.travel.daily.traveldaily.ToolUtils;
import com.travel.daily.traveldaily.R;
import com.travel.daily.traveldaily.bill.BillCreateActivity;
import com.travel.daily.traveldaily.dao.HotelBean;
import com.travel.daily.traveldaily.mvp.views.IView;

/**
 * Created on 16/5/23.
 */
public class HotelView extends IView<HotelBean> {
    Holder holder;
    HotelBean data;
    public HotelView(Context context) {
        super(context);
    }

    public HotelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HotelView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        holder.container = (RelativeLayout) findViewById(R.id.container);
    }

    @Override
    public void setData(HotelBean data) {
        this.data = data;
        holder.img.setImageBitmap(ToolUtils.getBitmapFromUrl(getContext(), data.getImgUrl()));
        holder.title.setText(data.getTitle());
        holder.detail.setText(data.getDetail());
        holder.action.setOnClickListener(this);
        holder.container.setOnClickListener(this);
    }

    public HotelBean getData() {
        return data;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.container:
                Intent detail = new Intent(getContext(), HotelDetailActivity.class);
                detail.putExtra("id", data.getId());
                getContext().startActivity(detail);
                break;
            case R.id.action:
                Intent creator = new Intent(getContext(), BillCreateActivity.class);
                creator.putExtra("id", data.getId());
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
        RelativeLayout container;
    }
}
