package com.travel.daily.traveldaily.debug;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.travel.daily.traveldaily.BaseAdapter;
import com.travel.daily.traveldaily.BaseListFragment;
import com.travel.daily.traveldaily.database.DBHelper;
import com.travel.daily.traveldaily.database.DataHelper;
import com.travel.daily.traveldaily.database.dao.DetailImage;
import com.travel.daily.traveldaily.database.dao.HotelBean;
import com.travel.daily.traveldaily.hotel.HotelView;
import com.travel.daily.traveldaily.mvp.presenters.BasePresenter;

import java.util.List;

/**
 * Created on 2016/5/29.
 */

public class DebugDeleteHotelFragment extends BaseListFragment {
    @Override
    protected RecyclerView.Adapter getAdapter() {
        return new HotelAdapter(getContext());
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
            final HotelView view = new HotelView(getActivity());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HotelBean bean = view.getData();
                    DataHelper.removeHotel(getContext(), bean.getId());
                    DataHelper.removeImage(getContext(), bean.getImgUrl());

                    DetailImage detail = DataHelper.loadDetailImage(getContext(), bean.getId());
                    DataHelper.removeDetailImage(getContext(), bean.getId());

                    DataHelper.removeImage(getContext(), detail.getPic0());
                    DataHelper.removeImage(getContext(), detail.getPic1());
                    DataHelper.removeImage(getContext(), detail.getPic2());

                    Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                    getAdapter().notifyDataSetChanged();
                    getAdapter().notifyItemRangeChanged(0, getAdapter().getItemCount());
                }
            });
            return view;
        }

        @Override
        public List<HotelBean> getList() {
            return DBHelper.getInstance(getContext()).getHotelDao().loadAll();
        }
    }
}
