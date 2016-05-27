package com.travel.daily.traveldaily;

import android.support.v7.widget.RecyclerView;

import com.travel.daily.traveldaily.mvp.views.IView;

/**
 * Created on 16/5/23.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
    public IView view;
    public BaseViewHolder(IView itemView) {
        super(itemView);
        this.view = itemView;
    }
}