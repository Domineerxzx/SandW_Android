package com.triplebro.aran.sandw.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.triplebro.aran.sandw.R;
import com.triplebro.aran.sandw.handlers.RegisterHandler;
import com.triplebro.aran.sandw.managers.RegisterManager;

public class RegisterActivity extends Activity implements View.OnClickListener {

    private ImageView iv_close_create;
    private Button bt_create;
    private Button bt_login;
    private RegisterManager registerManager;
    private EditText et_username;
    private EditText et_email;
    private EditText et_password;
    private String nickname;
    private String email;
    private String password;
    private CheckBox cb_agree;
    private RegisterHandler registerHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        setOnClickListener();
        initData();
    }

    private void initData() {
        registerHandler = new RegisterHandler(this);
    }

    private void setOnClickListener() {
        iv_close_create.setOnClickListener(this);
        bt_create.setOnClickListener(this);
        bt_login.setOnClickListener(this);
    }

    private void initView() {
        iv_close_create = (ImageView) findViewById(R.id.iv_close_create);
        bt_create = (Button) findViewById(R.id.bt_create);
        bt_login = (Button) findViewById(R.id.bt_login);
        et_username = (EditText) findViewById(R.id.et_username);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        cb_agree = (CheckBox) findViewById(R.id.cb_agree);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close_create:
                finish();
                break;
            case R.id.bt_create:
                nickname = et_username.getText().toString().trim();
                email = et_email.getText().toString().trim();
                password = et_password.getText().toString().trim();
                boolean checked = cb_agree.isChecked();
                if (!checked) {
                    Toast.makeText(this, "请阅读并同意条款", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (email.length() == 0) {
                    Toast.makeText(this, "电子邮箱不能为空", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (password.length() == 0) {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (nickname.length() == 0) {
                    Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (nickname.length() > 20) {
                    Toast.makeText(this, "用户名太长啦！！！", Toast.LENGTH_SHORT).show();
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
                    registerManager = new RegisterManager(this, registerHandler, nickname, email, password);
                    registerManager.register();
                    break;
                }
            case R.id.bt_login:
                Intent login = new Intent(this, LoginActivity.class);
                startActivity(login);
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (registerManager != null) {
            unbindService(registerManager);
        }
    }
}
