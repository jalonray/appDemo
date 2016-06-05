package com.travel.daily.traveldaily.search;

import android.hardware.input.InputManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.travel.daily.traveldaily.BaseActivity;
import com.travel.daily.traveldaily.R;

/**
 * Created on 2016/6/5.
 */

public class SearchActivity extends BaseActivity implements View.OnClickListener, TextView.OnEditorActionListener{

    TextInputEditText input;
    TextView search;
    SearchFragment fragment;
    InputMethodManager inputManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        if (savedInstanceState == null) {
            fragment = new SearchFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment).commit();
        }
        inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        input = (TextInputEditText) findViewById(R.id.search_txt);
        input.setOnEditorActionListener(this);
        search = (TextView) findViewById(R.id.search);
        search.setOnClickListener(this);
    }

    private void doSearch() {
        if (fragment != null) {
            fragment.text = input.getText().toString();
            fragment.refresh();
        } else {
            fragment = new SearchFragment();
            fragment.text = input.getText().toString();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment).commit();
        }
        inputManager.hideSoftInputFromWindow(input.getWindowToken(), 0);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.search) {
            doSearch();
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (TextUtils.isEmpty(v.getText())) {
            return false;
        } else {
            doSearch();
            return true;
        }
    }
}
