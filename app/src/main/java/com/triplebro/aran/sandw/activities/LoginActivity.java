package com.triplebro.aran.sandw.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.triplebro.aran.sandw.R;
import com.triplebro.aran.sandw.handlers.LoginHandler;
import com.triplebro.aran.sandw.managers.LoginManager;

public class LoginActivity extends Activity implements View.OnClickListener {

    private ImageView iv_close_login;
    private EditText et_email;
    private EditText et_password;
    private Button bt_login;
    private String email;
    private String password;
    private LoginManager loginManager;
    private Button bt_create;
    private Button bt_forget;
    private LoginHandler loginHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
        setOnClickListener();
    }

    private void initData() {
        loginHandler = new LoginHandler(this);
    }

    private void setOnClickListener() {
        iv_close_login.setOnClickListener(this);
        bt_login.setOnClickListener(this);
        bt_create.setOnClickListener(this);
        bt_forget.setOnClickListener(this);
    }

    private void initView() {
        iv_close_login = (ImageView) findViewById(R.id.iv_close_login);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_create = (Button) findViewById(R.id.bt_create);
        bt_forget = (Button) findViewById(R.id.bt_forget);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close_login:
                finish();
                break;
            case R.id.bt_login:
                email = et_email.getText().toString().trim();
                password = et_password.getText().toString().trim();
                if (email.length() == 0) {
                    Toast.makeText(this, "电子邮箱不能为空", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (password.length() == 0) {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (email.length() > 20) {
                    Toast.makeText(this, "电子邮箱太长啦！！！", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (password.length() > 20) {
                    Toast.makeText(this, "密码太长啦！！！", Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    loginManager = new LoginManager(this, loginHandler, email, password);
                    loginManager.login();
                    break;
                }
            case R.id.bt_create:
                Intent register = new Intent(this, RegisterActivity.class);
                startActivity(register);
                finish();
                break;
            case R.id.bt_forget:
                //TODO 跳转到忘记密码页面
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loginManager != null) {
            unbindService(loginManager);
        }
    }
}