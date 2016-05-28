package com.travel.daily.traveldaily.debug;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.travel.daily.traveldaily.BaseAdapter;
import com.travel.daily.traveldaily.BaseListFragment;
import com.travel.daily.traveldaily.DBHelper;
import com.travel.daily.traveldaily.DataHelper;
import com.travel.daily.traveldaily.dao.DelicacyBean;
import com.travel.daily.traveldaily.dao.DetailImage;
import com.travel.daily.traveldaily.delicacy.DelicacyView;
import com.travel.daily.traveldaily.mvp.presenters.BasePresenter;

import java.util.List;

/**
 * Created on 2016/5/29.
 */

public class DebugDeleteDelicacyFragment extends BaseListFragment {
    @Override
    protected RecyclerView.Adapter getAdapter() {
        return new DelicacyAdapter(getContext());
    }

    public class DelicacyAdapter extends BaseAdapter<DelicacyPresenter> {

        public DelicacyAdapter(Context context) {
            super(context);
        }

        @Override
        public DelicacyPresenter getPresenter() {
            return new DelicacyPresenter();
        }

    }

    public class DelicacyPresenter extends BasePresenter<DelicacyBean, DelicacyView> {

        @Override
        public DelicacyView createView() {
            final DelicacyView view = new DelicacyView(getActivity());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DelicacyBean bean = view.getData();
                    DataHelper.removeDelicacy(getContext(), bean.getId());
                    DataHelper.removeImage(getContext(), bean.getImgUrl());

                    DetailImage detail = DataHelper.loadDetailImage(getContext(), bean.getId());
                    DataHelper.removeDetailImage(getContext(), bean.getId());

                    DataHelper.removeImage(getContext(), detail.getPic0());
                    DataHelper.removeImage(getContext(), detail.getPic1());
                    DataHelper.removeImage(getContext(), detail.getPic2());
                }
            });
            return view;
        }

        @Override
        public List<DelicacyBean> getList() {
            return DBHelper.getInstance(getContext()).getDelicacyDao().loadAll();
        }
    }
}
