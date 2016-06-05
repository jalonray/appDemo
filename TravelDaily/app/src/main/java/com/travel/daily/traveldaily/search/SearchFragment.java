package com.travel.daily.traveldaily.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.travel.daily.traveldaily.BaseAdapter;
import com.travel.daily.traveldaily.BaseListFragment;
import com.travel.daily.traveldaily.database.DBHelper;
import com.travel.daily.traveldaily.database.dao.DelicacyBean;
import com.travel.daily.traveldaily.database.dao.HotelBean;
import com.travel.daily.traveldaily.database.dao.SceneryBean;
import com.travel.daily.traveldaily.mvp.presenters.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2016/6/5.
 */

public class SearchFragment extends BaseListFragment {
    String text;
    @Override
    protected RecyclerView.Adapter getAdapter() {
        return new SearchAdapter(getActivity());
    }

    public class SearchAdapter extends BaseAdapter<SearchPresenter> {

        public SearchAdapter(Context context) {
            super(context);
        }

        @Override
        public SearchPresenter getPresenter() {
            return new SearchPresenter();
        }

    }

    public class SearchPresenter extends BasePresenter<Object, SearchView> {

        @Override
        public SearchView createView() {
            return new SearchView(getActivity());

        }

        @Override
        public List<Object> getList() {
            if (TextUtils.isEmpty(text)) {
                return null;
            }
            List<Object> list = new ArrayList<>();
            List<SceneryBean> sceneryList = DBHelper.getInstance(getContext()).getSceneryDao().loadAll();
            for (SceneryBean sceneryBean : sceneryList) {
                if (isConform(sceneryBean)) {
                    list.add(sceneryBean);
                }
            }
            List<HotelBean> hotelList = DBHelper.getInstance(getContext()).getHotelDao().loadAll();
            for (HotelBean hotelBean : hotelList) {
                if (isConform(hotelBean)) {
                    list.add(hotelBean);
                }
            }
            List<DelicacyBean> delicacyList = DBHelper.getInstance(getContext()).getDelicacyDao().loadAll();
            for (DelicacyBean delicacyBean : delicacyList) {
                if (isConform(delicacyBean)) {
                    list.add(delicacyBean);
                }
            }
            return list;
        }

        private boolean isConform(SceneryBean bean) {
            return bean.getTitle().contains(text) || bean.getDetail().contains(text);
        }

        private boolean isConform(HotelBean bean) {
            return bean.getTitle().contains(text) || bean.getDetail().contains(text);
        }

        private boolean isConform(DelicacyBean bean) {
            return bean.getTitle().contains(text) || bean.getDetail().contains(text);
        }
    }
}
