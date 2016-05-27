package com.travel.daily.traveldaily.bill;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.travel.daily.traveldaily.ImageUtils;
import com.travel.daily.traveldaily.R;
import com.travel.daily.traveldaily.dao.BillBean;
import com.travel.daily.traveldaily.mvp.views.IView;

/**
 * Created on 16/5/24.
 */

public class BillView extends IView<BillBean> {
    Holder holder;
    BillBean data;
    public BillView(Context context) {
        super(context);
    }

    public BillView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BillView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_list_bill, this);
        holder = new Holder();
        holder.img = (ImageView) view.findViewById(R.id.img);
        holder.detail = (TextView) view.findViewById(R.id.detail);
        holder.time = (TextView) view.findViewById(R.id.time);
        holder.price = (TextView) view.findViewById(R.id.price);
        holder.name = (TextView) view.findViewById(R.id.name);
        holder.container = (RelativeLayout) view.findViewById(R.id.container);
    }

    @Override
    public void setData(BillBean data) {
        this.data = data;
        holder.img.setImageBitmap(ImageUtils.getBitmapFromUrl(getContext(), data.getImgUrl()));
        holder.name.setText(data.getName());
        holder.price.setText(getContext().getString(R.string.show_price, String.valueOf(data.getPrice())));
        holder.detail.setText(data.getDetail());
        holder.time.setText("2016年5月1日");
        holder.container.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.container: {
                Intent intent = new Intent(getContext(), BillDetailActivity.class);
                intent.putExtra("id", data.getId());
                getContext().startActivity(intent);
                break;
            }
            default: {
                break;
            }
        }
    }

    public class Holder {
        ImageView img;
        TextView name;
        TextView price;
        TextView detail;
        TextView time;
        RelativeLayout container;
    }
}
