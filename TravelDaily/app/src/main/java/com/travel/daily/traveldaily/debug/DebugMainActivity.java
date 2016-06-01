package com.travel.daily.traveldaily.debug;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.travel.daily.traveldaily.BaseActivity;
import com.travel.daily.traveldaily.R;

/**
 * Created on 2016/5/29.
 */

public class DebugMainActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug_main);
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                startActivity(new Intent(this, DebugAddActivity.class));
                break;
            case R.id.delete:
                startActivity(new Intent(this, DebugDeleteActivity.class));
                break;
            default:
                break;
        }
    }
}
