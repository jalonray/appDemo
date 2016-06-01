package com.travel.daily.traveldaily.scenery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.travel.daily.traveldaily.BaseAdapter;
import com.travel.daily.traveldaily.BaseListFragment;
import com.travel.daily.traveldaily.database.DBHelper;
import com.travel.daily.traveldaily.database.dao.SceneryBean;
import com.travel.daily.traveldaily.mvp.presenters.BasePresenter;

import java.util.List;

/**
 * Created on 16/5/19.
 */

public class SceneryListFragment extends BaseListFragment {
    @Override
    protected RecyclerView.Adapter getAdapter() {
        return new SceneryAdapter(getActivity());
    }

    public class SceneryAdapter extends BaseAdapter<SceneryPresenter> {

        public SceneryAdapter(Context context) {
            super(context);
        }

        @Override
        public SceneryPresenter getPresenter() {
            return new SceneryPresenter();
        }

    }

    public class SceneryPresenter extends BasePresenter<SceneryBean, SceneryView> {

        @Override
        public SceneryView createView() {
            return new SceneryView(getActivity());
        }

        @Override
        public List<SceneryBean> getList() {
            return DBHelper.getInstance(getContext()).getSceneryDao().loadAll();
        }
    }
}
