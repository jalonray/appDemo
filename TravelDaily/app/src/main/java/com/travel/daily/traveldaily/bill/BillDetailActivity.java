package com.travel.daily.traveldaily.bill;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.travel.daily.traveldaily.BaseActivity;
import com.travel.daily.traveldaily.DataHelper;
import com.travel.daily.traveldaily.ToolUtils;
import com.travel.daily.traveldaily.R;
import com.travel.daily.traveldaily.dao.BillBean;

import java.util.List;

/**
 * Created on 16/5/19.
 */

public class BillDetailActivity extends BaseActivity implements View.OnClickListener{

    FloatingActionButton addBill;
    long billId;
    BillBean data;
    ImageView backdrop;
    Toolbar toolbar;
    TextView detail;
    LinearLayout container;
    ImageView img;
    TextView price;
    TextView time;
    TextView name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_detail);
        backdrop = (ImageView) findViewById(R.id.backdrop);
        addBill = (FloatingActionButton) findViewById(R.id.add);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        detail = (TextView) findViewById(R.id.detail);
        container = (LinearLayout) findViewById(R.id.container);
        img = (ImageView) findViewById(R.id.img);
        price = (TextView) findViewById(R.id.price);
        time = (TextView) findViewById(R.id.time);
        name = (TextView) findViewById(R.id.name);
        if (getIntent() != null) {
            billId = getIntent().getLongExtra("id", -1);
        }
        showData();
    }

    private void showData() {
        data = DataHelper.loadBill(this, billId);
        if (data != null) {
            backdrop.setImageBitmap(ToolUtils.getBitmapFromUrl(this, data.getBgUrl()));
            img.setImageBitmap(ToolUtils.getBitmapFromUrl(this, data.getImgUrl()));
            price.setText(String.valueOf(data.getPrice()));
            time.setText(ToolUtils.getTimeString(data.getTime()));
            name.setText(data.getName());
            showSubBills();
        }
    }

    private void showSubBills() {
        List<BillBean> list = DataHelper.decodeBillList(data.getSubBill());
        if (list != null && list.size() != 0) {
            for (BillBean bean : list) {
                addSubBill(bean);
            }
        }
    }

    public void addSubBill(BillBean bean) {
        CardView card = (CardView) LayoutInflater.from(this).inflate(R.layout.card_bill_detail, container, false);
        ((ImageView) card.findViewById(R.id.img)).setImageBitmap(ToolUtils.getBitmapFromUrl(this, bean.getImgUrl()));
        ((TextView) card.findViewById(R.id.price)).setText(String.valueOf(bean.getPrice()));
        ((TextView) card.findViewById(R.id.name)).setText(bean.getName());
        ((TextView) card.findViewById(R.id.time)).setText(ToolUtils.getTimeString(bean.getTime()));
        container.addView(card);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                BillAddDialogFragment.newInstance(billId).show(getSupportFragmentManager(), "addBill");
                break;
            default:
                break;
        }
    }

    public static class BillAddDialogFragment extends DialogFragment implements View.OnClickListener{
        TextInputEditText inputPrice;
        TextInputEditText inputName;
        TextView showPrice;
        ImageView showImg;
        TextView addBill;
        long billId;
        float needPrice;

        public static BillAddDialogFragment newInstance(long id) {

            Bundle args = new Bundle();
            args.putLong("id", id);
            BillAddDialogFragment fragment = new BillAddDialogFragment();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                billId = getArguments().getLong("id", -1);
            }
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.sub_bill_add_dialog, container, false);
            inputPrice = (TextInputEditText) view.findViewById(R.id.price);
            inputName = (TextInputEditText) view.findViewById(R.id.name);
            showPrice = (TextView) view.findViewById(R.id.needPrice);
            showImg = (ImageView) view.findViewById(R.id.img);
            addBill = (TextView) view.findViewById(R.id.add);
            addBill.setOnClickListener(this);
            return view;
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            showPrice.setText(String.valueOf(needPrice));
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == ToolUtils.SELECT_PICTURE) {
                showImg.setImageBitmap(ToolUtils.getBitmapFromIntent(getContext(), data));
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.add:
                    if (billId == -1) {
                        Toast.makeText(getContext(), "账单无效", Toast.LENGTH_SHORT).show();
                        dismiss();
                        return;
                    }
                    float price = Float.parseFloat(inputPrice.getText().toString());
                    // 判断价格合理性
                    if (price <= 0) {
                        Toast.makeText(getContext(), "价格无效", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (price > needPrice) {
                        Toast.makeText(getContext(), "价格超出", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // 添加新字账单到数据库
                    BillBean billBean = new BillBean();
                    billBean.setImgUrl(ToolUtils.saveBitmap(getContext(), showImg.getDrawingCache()));
                    billBean.setName(inputName.getText().toString());
                    billBean.setTime(SystemClock.currentThreadTimeMillis());

                    BillBean currentBean = DataHelper.loadBill(getContext(), billId);
                    List<BillBean> list = DataHelper.decodeBillList(currentBean.getSubBill());
                    list.add(billBean);
                    currentBean.setSubBill(DataHelper.encodeBillList(list));
                    DataHelper.insertBill(getContext(), currentBean);

                    // 界面显示新子账单
                    ((BillDetailActivity) getActivity()).addSubBill(billBean);
                    dismiss();
                    break;
                case R.id.img:
                    ToolUtils.getPicFromGallery(this);
                    break;
                default:
                    break;
            }
        }
    }
}
