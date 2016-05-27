package com.travel.daily.traveldaily.mvp.presenters;

import com.travel.daily.traveldaily.mvp.views.IView;

import java.util.List;

/**
 * Created on 16/5/19.
 */

public abstract class BasePresenter<D, V extends IView<D>> {
    public abstract V createView();
    public abstract List<D> getList();
    public V getView() {
        return createView();
    }
    public D getData(int position) {
        List<D> list = getList();
        if (list == null || position >= list.size()) {
            return null;
        }
        return list.get(position);
    }
    public int getItemCount() {
        return getList() == null ? 0 : getList().size();
    }
}
