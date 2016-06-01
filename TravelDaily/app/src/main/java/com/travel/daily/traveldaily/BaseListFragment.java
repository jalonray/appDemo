package com.travel.daily.traveldaily;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created on 16/5/19.
 */

public abstract class BaseListFragment extends BaseFragment {

    RecyclerView rcView;
    TextView fresh;

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        rcView = (RecyclerView) view.findViewById(R.id.list);
        fresh = (TextView) view.findViewById(R.id.refresh);
        fresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        rcView.setLayoutManager(new LinearLayoutManager(getContext()));
        refresh();
        super.onViewCreated(view, savedInstanceState);
    }

    protected abstract RecyclerView.Adapter getAdapter();

    public void refresh() {
        if (rcView == null) {
            return;
        }
        rcView.setAdapter(getAdapter());
        if (getAdapter().getItemCount() == 0) {
            fresh.setVisibility(View.VISIBLE);
            rcView.setVisibility(View.GONE);
        } else {
            fresh.setVisibility(View.GONE);
            rcView.setVisibility(View.VISIBLE);
        }
    }

}
