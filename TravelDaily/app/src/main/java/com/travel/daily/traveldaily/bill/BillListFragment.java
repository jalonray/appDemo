package com.travel.daily.traveldaily.bill;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.travel.daily.traveldaily.account.AccountManager;
import com.travel.daily.traveldaily.BaseAdapter;
import com.travel.daily.traveldaily.BaseListFragment;
import com.travel.daily.traveldaily.database.DataHelper;
import com.travel.daily.traveldaily.database.dao.AccountBean;
import com.travel.daily.traveldaily.database.dao.BillBean;
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
            AccountBean bean = AccountManager.getInstance(getContext()).getBean();
            if (bean == null) {
                return null;
            }
            return DataHelper.loadBillList(getContext(),
                    bean.getId());
        }
    }
}
