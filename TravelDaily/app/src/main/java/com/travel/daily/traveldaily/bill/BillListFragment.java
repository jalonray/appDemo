package com.travel.daily.traveldaily.bill;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.travel.daily.traveldaily.BaseAdapter;
import com.travel.daily.traveldaily.BaseListFragment;
import com.travel.daily.traveldaily.DBHelper;
import com.travel.daily.traveldaily.dao.BillBean;
import com.travel.daily.traveldaily.dao.BillList;
import com.travel.daily.traveldaily.mvp.presenters.BasePresenter;

import java.util.List;

/**
 * Created on 16/5/19.
 */

public class BillListFragment extends BaseListFragment {
    @Override
    protected RecyclerView.Adapter getAdapter() {
        return new BillAdapter(getActivity());
    }

    private class BillAdapter extends BaseAdapter<BillPresenter> {

        BillAdapter(Context context) {
            super(context);
        }

        @Override
        public BillPresenter getPresenter() {
            return new BillPresenter();
        }

    }

    private class BillPresenter extends BasePresenter<BillBean, BillView> {

        @Override
        public BillView createView() {
            return new BillView(getActivity());
        }

        @Override
        public List<BillBean> getList() {
            BillList billList = DBHelper.getInstance(getContext()).getBillListDao().load("key");
            return new Gson().fromJson(billList.getBillList(), new TypeToken<List<BillBean>>(){}.getType());
        }
    }
}
