package com.travel.daily.traveldaily.hotel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.travel.daily.traveldaily.BaseAdapter;
import com.travel.daily.traveldaily.BaseListFragment;
import com.travel.daily.traveldaily.database.DBHelper;
import com.travel.daily.traveldaily.database.dao.HotelBean;
import com.travel.daily.traveldaily.mvp.presenters.BasePresenter;

import java.util.List;

/**
 * Created on 16/5/19.
 */

public class HotelListFragment extends BaseListFragment {
    @Override
    protected RecyclerView.Adapter getAdapter() {
        return new HotelAdapter(getActivity());
    }

    public class HotelAdapter extends BaseAdapter<HotelPresenter> {

        public HotelAdapter(Context context) {
            super(context);
        }

        @Override
        public HotelPresenter getPresenter() {
            return new HotelPresenter();
        }

    }

    public class HotelPresenter extends BasePresenter<HotelBean, HotelView> {

        @Override
        public HotelView createView() {
            return new HotelView(getActivity());
        }

        @Override
        public List<HotelBean> getList() {
            return DBHelper.getInstance(getContext()).getHotelDao().loadAll();
        }
    }
}
