package com.triplebro.aran.sandw.handlers;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.triplebro.aran.sandw.beans.UserInfo;
import com.triplebro.aran.sandw.managers.UserManager;
import com.triplebro.aran.sandw.properties.AppProperties;

public class UserHandler extends Handler {

    private Context context;
    private TextView tv_username;
    private TextView tv_email;
    private TextView tv_cancellation;
    private RadioButton rbt_sex_f;
    private RadioButton rbt_sex_m;
    private TextView tv_birth;
    private EditText et_username;
    private TextView et_email;
    private UserManager userManager;

    public UserHandler(Context context, TextView tv_username, TextView tv_email, TextView tv_cancellation, RadioButton rbt_sex_f, RadioButton rbt_sex_m) {
        this.context = context;
        this.tv_username = tv_username;
        this.tv_email = tv_email;
        this.tv_cancellation = tv_cancellation;
        this.rbt_sex_f = rbt_sex_f;
        this.rbt_sex_m = rbt_sex_m;
    }

    public UserHandler(Context context, TextView tv_birth, EditText et_username, TextView et_email) {
        this.context = context;
        this.tv_birth = tv_birth;
        this.et_username = et_username;
        this.et_email = et_email;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public void handleMessage(Message msg) {
        UserInfo.UserInfoBean userInfo = (UserInfo.UserInfoBean) msg.obj;
        switch (msg.what) {
            case AppProperties.UPDATE_USER_INFO_WHAT_OUTSIDE:
                this.tv_username.setText(userInfo.getNickName());
                this.tv_email.setText("/" + userInfo.getUserName());
                this.tv_cancellation.setText("不是" + userInfo.getNickName() + "吗？");
                System.out.println("-------------------------------" + userInfo.getSex().toString());
                if (userInfo.getSex().toString().equals("1.0")) {
                    rbt_sex_f.setChecked(true);
                    rbt_sex_m.setChecked(false);
                } else {
                    rbt_sex_m.setChecked(true);
                    rbt_sex_f.setChecked(false);
                }
                context.unbindService(userManager);
                break;
            case AppProperties.UPDATE_USER_INFO_WHAT_INSIDE:
                this.et_username.setText(userInfo.getNickName());
                this.et_email.setText(userInfo.getUserName());
                Object birthday = userInfo.getBirthday();
                if (birthday != null) {
                    this.tv_birth.setText(birthday.toString());
                }
                break;
        }
    }
}
