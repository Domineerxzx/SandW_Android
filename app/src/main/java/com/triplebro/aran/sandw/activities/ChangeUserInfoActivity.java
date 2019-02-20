package com.triplebro.aran.sandw.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.triplebro.aran.sandw.R;
import com.triplebro.aran.sandw.handlers.ChangeInfoHandler;
import com.triplebro.aran.sandw.handlers.UserHandler;
import com.triplebro.aran.sandw.managers.ChangeInfoManager;
import com.triplebro.aran.sandw.managers.UserManager;
import com.triplebro.aran.sandw.properties.AppProperties;
import com.triplebro.aran.sandw.views.DatePickerListener;

import java.util.Calendar;

public class ChangeUserInfoActivity extends Activity implements View.OnClickListener {

    private TextView tv_birth;
    private EditText et_email;
    private EditText et_username;
    private ImageView iv_close_user_info;
    private ImageView iv_birth;
    private int year_now;
    private int month_now;
    private int day_now;
    private Button bt_change_password;
    private UserHandler userHandler;
    private UserManager userManager;
    private Button bt_change_info;
    private String nickname;
    private String email;
    private String birthday;
    private ChangeInfoHandler changeInfoHandler;
    private ChangeInfoManager changeInfoManager;
    private String sessionInfo;
    private SharedPreferences session;
    private SharedPreferences sex;
    private String sexInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_info);
        initView();
        initData();
        setOnClickListener();
    }


    private void setOnClickListener() {
        iv_close_user_info.setOnClickListener(this);
        tv_birth.setOnClickListener(this);
        iv_birth.setOnClickListener(this);
        bt_change_password.setOnClickListener(this);
        bt_change_info.setOnClickListener(this);
    }

    private void initData() {
        session = getSharedPreferences("session", MODE_PRIVATE);
        sessionInfo = session.getString("session", null);
        if (sessionInfo != null) {
            userHandler = new UserHandler(this, tv_birth, et_username, et_email);
            userManager = new UserManager(this, userHandler, sessionInfo, AppProperties.UPDATE_USER_INFO_WHAT_INSIDE);
            userManager.showUserInfo();
        }
        sex = getSharedPreferences("sex", MODE_PRIVATE);
        sexInfo = sex.getString("sex", null);
        Calendar calendar = Calendar.getInstance();
        year_now = calendar.get(Calendar.YEAR);
        month_now = calendar.get(Calendar.MONTH);
        day_now = calendar.get(Calendar.DAY_OF_MONTH);

    }

    private void initView() {
        et_username = (EditText) findViewById(R.id.et_username);
        et_email = (EditText) findViewById(R.id.et_email);
        tv_birth = (TextView) findViewById(R.id.tv_birth);
        iv_birth = (ImageView) findViewById(R.id.iv_birth);
        iv_close_user_info = (ImageView) findViewById(R.id.iv_close_user_info);
        bt_change_password = (Button) findViewById(R.id.bt_change_password);
        bt_change_info = (Button) findViewById(R.id.bt_change_info);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close_user_info:
                finish();
                break;
            case R.id.tv_birth:
            case R.id.iv_birth:
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.MyDatePickerDialogTheme,
                        new DatePickerListener(tv_birth, year_now, month_now, day_now), year_now, month_now, day_now);
                datePickerDialog.show();

                break;
            case R.id.bt_change_password:
                Intent intent = new Intent(this, ChangePassWordActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_change_info:
                nickname = et_username.getText().toString().trim();
                email = et_email.getText().toString().trim();
                birthday = tv_birth.getText().toString().trim();
                changeInfoHandler = new ChangeInfoHandler(this);
                changeInfoManager = new ChangeInfoManager(this, changeInfoHandler, nickname, email, birthday, sexInfo, sessionInfo);
                changeInfoManager.changeInfo();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(userManager);
        if (changeInfoManager != null) {
            unbindService(changeInfoManager);
        }
    }
}
