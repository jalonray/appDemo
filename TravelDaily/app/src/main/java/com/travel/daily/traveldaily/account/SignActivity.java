package com.travel.daily.traveldaily.account;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.travel.daily.traveldaily.BaseActivity;
import com.travel.daily.traveldaily.database.DataHelper;
import com.travel.daily.traveldaily.MainActivity;
import com.travel.daily.traveldaily.R;
import com.travel.daily.traveldaily.ToolUtils;
import com.travel.daily.traveldaily.database.dao.AccountBean;

/**
 * Created on 2016/5/29.
 */

public class SignActivity extends BaseActivity implements View.OnClickListener{
    ImageView img;
    TextInputEditText name;
    TextInputEditText password0;
    TextInputEditText password1;
    TextView sign;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        img = (ImageView) findViewById(R.id.img);
        name = (TextInputEditText) findViewById(R.id.name);
        password0 = (TextInputEditText) findViewById(R.id.password0);
        password1 = (TextInputEditText) findViewById(R.id.password1);
        sign = (TextView) findViewById(R.id.sign);

        img.setOnClickListener(this);
        sign.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ToolUtils.SELECT_PICTURE) {
            img.setImageBitmap(ToolUtils.getBitmapFromIntent(this, data));
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img:
                ToolUtils.getPicFromGallery(this);
                break;
            case R.id.sign:
                String nameStr = name.getText().toString();
                if (TextUtils.isEmpty(nameStr)) {
                    Toast.makeText(this, "用户名为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String pwdStr0 = password0.getText().toString();
                if (TextUtils.isEmpty(pwdStr0)) {
                    Toast.makeText(this, "输入密码为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String pwdStr1 = password1.getText().toString();
                if (TextUtils.isEmpty(pwdStr1)) {
                    Toast.makeText(this, "确认密码为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.equals(pwdStr0, pwdStr1)) {
                    Toast.makeText(this, "密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }

                AccountBean bean = new AccountBean();
                bean.setId(System.currentTimeMillis());
                bean.setName(nameStr);
                bean.setPassword(pwdStr0);
                bean.setImgUrl(ToolUtils.saveBitmap(this, img));
                DataHelper.insertAccount(this, bean);
                AccountManager.getInstance(this).setBean(bean);
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
