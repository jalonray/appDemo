package com.travel.daily.traveldaily.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.travel.daily.traveldaily.BaseActivity;
import com.travel.daily.traveldaily.database.DataHelper;
import com.travel.daily.traveldaily.R;
import com.travel.daily.traveldaily.database.dao.AccountBean;

import java.util.List;

/**
 * Created on 16/5/19.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    TextInputEditText name;
    TextInputEditText password;
    TextView sign;
    TextView login;
    AccountBean account;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = (TextInputEditText) findViewById(R.id.name);
        password = (TextInputEditText) findViewById(R.id.password);
        sign = (TextView) findViewById(R.id.sign);
        login = (TextView) findViewById(R.id.login);

        sign.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign:
                startActivity(new Intent(this, SignActivity.class));
                break;
            case R.id.login:
                String nameStr = name.getText().toString();
                if (TextUtils.isEmpty(nameStr)) {
                    Toast.makeText(this, "登录名为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String pwdStr = password.getText().toString();
                if (TextUtils.isEmpty(pwdStr)) {
                    Toast.makeText(this, "密码为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<AccountBean> list = DataHelper.getAllAccounts(this);
                for (AccountBean bean : list) {
                    if (TextUtils.equals(bean.getName(), nameStr) &&
                            TextUtils.equals(bean.getPassword(), pwdStr)) {
                        account = bean;
                        AccountManager.getInstance(this).setBean(account);
                        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                        return;
                    }
                }
                Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
