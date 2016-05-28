package com.travel.daily.traveldaily.debug;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.travel.daily.traveldaily.BaseAdapter;
import com.travel.daily.traveldaily.BaseListFragment;
import com.travel.daily.traveldaily.DBHelper;
import com.travel.daily.traveldaily.DataHelper;
import com.travel.daily.traveldaily.dao.DetailImage;
import com.travel.daily.traveldaily.dao.SceneryBean;
import com.travel.daily.traveldaily.mvp.presenters.BasePresenter;
import com.travel.daily.traveldaily.scenery.SceneryView;

import java.util.List;

/**
 * Created on 2016/5/29.
 */

public class DebugDeleteSceneryFragment extends BaseListFragment {
    @Override
    protected RecyclerView.Adapter getAdapter() {
        return new SceneryAdapter(getContext());
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
            final SceneryView view = new SceneryView(getActivity());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SceneryBean bean = view.getData();
                    DataHelper.removeScenery(getContext(), bean.getId());
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
        public List<SceneryBean> getList() {
            return DBHelper.getInstance(getContext()).getSceneryDao().loadAll();
        }
    }
}
