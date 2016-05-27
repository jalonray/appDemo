package com.travel.daily.traveldaily;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created on 16/5/19.
 */

public abstract class BaseListFragment extends BaseFragment {

    RecyclerView rcView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rcView = (RecyclerView) inflater.inflate(R.layout.list_fragment, container, false);
        return rcView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        rcView.setAdapter(getAdapter());
        rcView.setLayoutManager(new LinearLayoutManager(getContext()));
        super.onViewCreated(view, savedInstanceState);
    }

    protected abstract RecyclerView.Adapter getAdapter();

}
