package com.travel.daily.traveldaily;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.travel.daily.traveldaily.mvp.presenters.BasePresenter;

/**
 * Created on 16/5/20.
 */

public abstract class BaseAdapter<P extends BasePresenter> extends RecyclerView.Adapter<BaseViewHolder> {
    public Context context;

    public BaseAdapter(Context context){
        this.context = context;
    }

    public abstract P getPresenter();

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(getPresenter().getView());
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.view.setData(getPresenter().getData(position));
    }

    @Override
    public int getItemCount() {
        return getPresenter().getItemCount();
    }
}
