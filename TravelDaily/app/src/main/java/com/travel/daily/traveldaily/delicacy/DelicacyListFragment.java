package com.travel.daily.traveldaily.delicacy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.travel.daily.traveldaily.BaseAdapter;
import com.travel.daily.traveldaily.BaseListFragment;
import com.travel.daily.traveldaily.database.DBHelper;
import com.travel.daily.traveldaily.database.dao.DelicacyBean;
import com.travel.daily.traveldaily.mvp.presenters.BasePresenter;

import java.util.List;

/**
 * Created on 16/5/19.
 */

public class DelicacyListFragment extends BaseListFragment {
    @Override
    protected RecyclerView.Adapter getAdapter() {
        return new DelicacyAdapter(getActivity());
    }


    private class DelicacyAdapter extends BaseAdapter<DelicacyPresenter> {

        DelicacyAdapter(Context context) {
            super(context);
        }

        @Override
        public DelicacyPresenter getPresenter() {
            return new DelicacyPresenter();
        }

    }


    private class DelicacyPresenter extends BasePresenter<DelicacyBean, DelicacyView> {

        @Override
        public DelicacyView createView() {
            return new DelicacyView(getActivity());
        }

        @Override
        public List<DelicacyBean> getList() {
            return DBHelper.getInstance(getContext()).getDelicacyDao().loadAll();
        }
    }
}
