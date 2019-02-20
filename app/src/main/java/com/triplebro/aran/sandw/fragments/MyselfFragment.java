package com.triplebro.aran.sandw.fragments;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.triplebro.aran.sandw.activities.AddressActivity;
import com.triplebro.aran.sandw.activities.OrderActivity;
import com.triplebro.aran.sandw.handlers.UserHandler;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.triplebro.aran.sandw.R;
import com.triplebro.aran.sandw.activities.ChangeUserInfoActivity;
import com.triplebro.aran.sandw.activities.LoginActivity;
import com.triplebro.aran.sandw.activities.RegisterActivity;
import com.triplebro.aran.sandw.managers.UserManager;
import com.triplebro.aran.sandw.properties.AppProperties;
import com.triplebro.aran.sandw.utils.permissionUtils.PermissionUtils;
import com.triplebro.aran.sandw.views.TwoButtonDialog;


public class MyselfFragment extends Fragment implements View.OnClickListener {

    private View fragment_myself;
    private Button bt_create;
    private Button bt_login;
    private RadioButton rbt_sex_f;
    private RadioButton rbt_sex_m;
    private TextView tv_sex_f;
    private TextView tv_sex_m;
    private LinearLayout ll_contact_phone;
    private ImageView iv_contact_phone;
    private TextView tv_contact_phone;
    private LinearLayout ll_contact_email;
    private ImageView iv_contact_email;
    private TextView tv_contact_email;
    private TextView tv_title;
    private RelativeLayout rl_about_us;
    private TextView tv_about_us;
    private ImageView iv_about_us_more;
    private LinearLayout ll_unlogin;
    private RelativeLayout rl_login;
    private LinearLayout ll_cancellation;
    private Button bt_cancellation;
    private SharedPreferences session;
    private TextView tv_username;
    private TextView tv_email;
    private TextView tv_user;
    private ImageView iv_user_more;
    private TextView tv_cancellation;
    private RelativeLayout rl_clause;
    private TextView tv_clause;
    private ImageView iv_clause_more;
    private RelativeLayout rl_privacy;
    private TextView tv_privacy;
    private ImageView iv_privacy_more;
    private RelativeLayout rl_guide;
    private TextView tv_guide;
    private ImageView iv_guide_more;
    private RelativeLayout rl_partner;
    private TextView tv_partner;
    private ImageView iv_partner_more;
    private TextView tv_address;
    private ImageView iv_address_more;
    private String session_now;
    private TextView tv_order;
    private ImageView iv_order;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragment_myself = inflater.inflate(R.layout.fragment_myself, null);
        initView();
        setOnClickListener();
        initData();
        return fragment_myself;
    }

    private void initData() {
        tv_title.setText(R.string.title_myself);
        SharedPreferences sex = getActivity().getSharedPreferences("sex", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sex.edit();
        edit.putString("sex", "Girl");
        edit.commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("--------------------onStart-------------------------");
        //TODO 重新请求，查看本地是否存在session,如果有就请求加载个人信息
        //TODO 同时更新UI.可视化注销及个人信息控件

        if (session == null) {
            session = getActivity().getSharedPreferences("session", Context.MODE_PRIVATE);
        }
        session_now = session.getString("session", null);
        if (session_now != null) {
            ll_unlogin.setVisibility(View.GONE);
            rl_login.setVisibility(View.VISIBLE);
            ll_cancellation.setVisibility(View.VISIBLE);
            UserHandler userHandler = new UserHandler(getActivity(), tv_username, tv_email, tv_cancellation, rbt_sex_f, rbt_sex_m);
            UserManager userManager = new UserManager(getActivity(), userHandler, session_now, AppProperties.UPDATE_USER_INFO_WHAT_OUTSIDE);
            userHandler.setUserManager(userManager);
            userManager.showUserInfo();
        }
    }


    private void setOnClickListener() {
        bt_create.setOnClickListener(this);
        bt_login.setOnClickListener(this);
        rbt_sex_f.setOnClickListener(this);
        rbt_sex_m.setOnClickListener(this);
        tv_sex_f.setOnClickListener(this);
        tv_sex_m.setOnClickListener(this);
        ll_contact_phone.setOnClickListener(this);
        iv_contact_phone.setOnClickListener(this);
        tv_contact_phone.setOnClickListener(this);
        ll_contact_email.setOnClickListener(this);
        iv_contact_email.setOnClickListener(this);
        tv_contact_email.setOnClickListener(this);
        rl_about_us.setOnClickListener(this);
        tv_about_us.setOnClickListener(this);
        iv_about_us_more.setOnClickListener(this);
        rl_clause.setOnClickListener(this);
        tv_clause.setOnClickListener(this);
        iv_clause_more.setOnClickListener(this);
        rl_privacy.setOnClickListener(this);
        tv_privacy.setOnClickListener(this);
        iv_privacy_more.setOnClickListener(this);
        rl_guide.setOnClickListener(this);
        tv_guide.setOnClickListener(this);
        iv_guide_more.setOnClickListener(this);
        rl_partner.setOnClickListener(this);
        tv_partner.setOnClickListener(this);
        iv_partner_more.setOnClickListener(this);
        bt_cancellation.setOnClickListener(this);
        tv_user.setOnClickListener(this);
        iv_user_more.setOnClickListener(this);
        tv_address.setOnClickListener(this);
        iv_address_more.setOnClickListener(this);
        tv_order.setOnClickListener(this);
        iv_order.setOnClickListener(this);
    }

    private void initView() {
        bt_create = fragment_myself.findViewById(R.id.bt_create);
        bt_login = fragment_myself.findViewById(R.id.bt_login);
        rbt_sex_f = fragment_myself.findViewById(R.id.rbt_sex_f);
        rbt_sex_m = fragment_myself.findViewById(R.id.rbt_sex_m);
        tv_sex_f = fragment_myself.findViewById(R.id.tv_sex_f);
        tv_sex_m = fragment_myself.findViewById(R.id.tv_sex_m);
        ll_contact_phone = fragment_myself.findViewById(R.id.ll_contact_phone);
        iv_contact_phone = fragment_myself.findViewById(R.id.iv_contact_phone);
        tv_contact_phone = fragment_myself.findViewById(R.id.tv_contact_phone);
        ll_contact_email = fragment_myself.findViewById(R.id.ll_contact_email);
        iv_contact_email = fragment_myself.findViewById(R.id.iv_contact_email);
        tv_contact_email = fragment_myself.findViewById(R.id.tv_contact_email);
        tv_title = getActivity().findViewById(R.id.tv_title);
        rl_about_us = fragment_myself.findViewById(R.id.rl_about_us);
        tv_about_us = fragment_myself.findViewById(R.id.tv_about_us);
        iv_about_us_more = fragment_myself.findViewById(R.id.iv_about_us_more);
        ll_unlogin = fragment_myself.findViewById(R.id.ll_unlogin);
        rl_login = fragment_myself.findViewById(R.id.rl_login);
        ll_cancellation = fragment_myself.findViewById(R.id.ll_cancellation);
        bt_cancellation = fragment_myself.findViewById(R.id.bt_cancellation);
        tv_username = fragment_myself.findViewById(R.id.tv_username);
        tv_email = fragment_myself.findViewById(R.id.tv_email);
        tv_user = fragment_myself.findViewById(R.id.tv_user);
        iv_user_more = fragment_myself.findViewById(R.id.iv_user_more);
        tv_cancellation = fragment_myself.findViewById(R.id.tv_cancellation);
        rl_clause = fragment_myself.findViewById(R.id.rl_clause);
        tv_clause = fragment_myself.findViewById(R.id.tv_clause);
        iv_clause_more = fragment_myself.findViewById(R.id.iv_clause_more);
        rl_privacy = fragment_myself.findViewById(R.id.rl_privacy);
        tv_privacy = fragment_myself.findViewById(R.id.tv_privacy);
        iv_privacy_more = fragment_myself.findViewById(R.id.iv_privacy_more);
        rl_guide = fragment_myself.findViewById(R.id.rl_guide);
        tv_guide = fragment_myself.findViewById(R.id.tv_guide);
        iv_guide_more = fragment_myself.findViewById(R.id.iv_guide_more);
        rl_partner = fragment_myself.findViewById(R.id.rl_partner);
        tv_partner = fragment_myself.findViewById(R.id.tv_partner);
        iv_partner_more = fragment_myself.findViewById(R.id.iv_partner_more);
        tv_address = fragment_myself.findViewById(R.id.tv_address);
        iv_address_more = fragment_myself.findViewById(R.id.iv_address_more);
        tv_order = (TextView) fragment_myself.findViewById(R.id.tv_order);
        iv_order = (ImageView) fragment_myself.findViewById(R.id.iv_order);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_create:
                Intent register = new Intent(getActivity(), RegisterActivity.class);
                startActivity(register);
                break;
            case R.id.bt_login:
                Intent login = new Intent(getActivity(), LoginActivity.class);
                startActivity(login);
                break;
            case R.id.bt_cancellation:
                TwoButtonDialog cancellation = new TwoButtonDialog();
                cancellation.show("注销账号", "确定要注销该账号吗？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor edit = session.edit();
                        edit.clear();
                        edit.commit();

                        rl_login.setVisibility(View.GONE);
                        ll_unlogin.setVisibility(View.VISIBLE);
                        ll_cancellation.setVisibility(View.GONE);
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "取消注销", Toast.LENGTH_SHORT).show();
                    }
                }, getActivity().getFragmentManager());

                break;
            case R.id.tv_order:
            case R.id.iv_order:
                Intent order = new Intent(getActivity(), OrderActivity.class);
                getActivity().startActivity(order);
                break;
            case R.id.tv_sex_f:
            case R.id.rbt_sex_f:
                TwoButtonDialog choose_sex_f = new TwoButtonDialog();
                choose_sex_f.show("修改性别为女", "我们将优先展示女士单品，确定要修改吗？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean checked_f = rbt_sex_f.isChecked();
                        if (checked_f) {
                            rbt_sex_m.setChecked(false);
                        } else {
                            rbt_sex_f.setChecked(true);
                            rbt_sex_m.setChecked(false);
                        }
                        SharedPreferences sex = getActivity().getSharedPreferences("sex", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = sex.edit();
                        edit.putString("sex", "Girl");
                        edit.commit();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "取消修改", Toast.LENGTH_SHORT).show();
                    }
                }, getActivity().getFragmentManager());
                break;
            case R.id.tv_sex_m:
            case R.id.rbt_sex_m:
                TwoButtonDialog choose_sex_m = new TwoButtonDialog();
                choose_sex_m.show("修改性别为男", "我们将优先展示男士单品，确定要修改吗？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean checked_m = rbt_sex_m.isChecked();
                        if (checked_m) {
                            rbt_sex_f.setChecked(false);
                        } else {
                            rbt_sex_f.setChecked(false);
                            rbt_sex_m.setChecked(true);
                        }
                        SharedPreferences sex = getActivity().getSharedPreferences("sex", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = sex.edit();
                        edit.putString("sex", "Man");
                        edit.commit();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "取消修改", Toast.LENGTH_SHORT).show();
                    }
                }, getActivity().getFragmentManager());
                break;
            case R.id.ll_contact_phone:
            case R.id.iv_contact_phone:
            case R.id.tv_contact_phone:
                PermissionUtils.requestCallPhonePermission(getActivity(), getActivity());
                TwoButtonDialog contact_us = new TwoButtonDialog();
                String title = "联系我们";
                String message = "拨打电话：18840919546";
                final String telephone = "18840919546";
                contact_us.show(title, message, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (ContextCompat.checkSelfPermission(getActivity(),
                                Manifest.permission.CALL_PHONE)
                                != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(getActivity(), "未获得权限,请设置开启此权限", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:" + telephone));
                            getActivity().startActivity(intent);
                        }
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "取消呼叫", Toast.LENGTH_SHORT).show();
                    }
                }, getActivity().getFragmentManager());
                break;
            case R.id.ll_contact_email:
            case R.id.iv_contact_email:
            case R.id.tv_contact_email:
                //TODO 调用隐式意图发邮件
                Intent it = new Intent(Intent.ACTION_SEND);
                it.putExtra(Intent.EXTRA_EMAIL, "374448535@qq.com");
                it.putExtra(Intent.EXTRA_TEXT, "XXXXXXXXXXXXXXXXXXXXXX");
                it.setType("text/plain");
                startActivity(Intent.createChooser(it, "Choose Email Client"));
                break;
            case R.id.rl_about_us:
            case R.id.tv_about_us:
            case R.id.iv_about_us_more:
                TwoButtonDialog about_us = new TwoButtonDialog();
                about_us.show("您即将退出应用程序，前往S&W官网", "确定？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri uri = Uri.parse("http://www.thethreestooges.cn");
                        Intent it = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(it);
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "取消访问", Toast.LENGTH_SHORT).show();
                    }
                }, getActivity().getFragmentManager());
                break;
            case R.id.tv_user:
            case R.id.iv_user_more:
                Intent intent = new Intent(getActivity(), ChangeUserInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_clause:
            case R.id.tv_clause:
            case R.id.iv_clause_more:
                TwoButtonDialog clause = new TwoButtonDialog();
                clause.show("您即将退出应用程序，前往S&W官网", "确定？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri uri = Uri.parse("http://www.thethreestooges.cn");
                        Intent it = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(it);
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "取消访问", Toast.LENGTH_SHORT).show();
                    }
                }, getActivity().getFragmentManager());
                break;
            case R.id.rl_privacy:
            case R.id.tv_privacy:
            case R.id.iv_privacy_more:
                TwoButtonDialog privacy = new TwoButtonDialog();
                privacy.show("您即将退出应用程序，前往S&W官网", "确定？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri uri = Uri.parse("http://www.thethreestooges.cn");
                        Intent it = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(it);
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "取消访问", Toast.LENGTH_SHORT).show();
                    }
                }, getActivity().getFragmentManager());
                break;
            case R.id.rl_guide:
            case R.id.tv_guide:
            case R.id.iv_guide_more:
                TwoButtonDialog guide = new TwoButtonDialog();
                guide.show("您即将退出应用程序，前往S&W官网", "确定？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri uri = Uri.parse("http://www.thethreestooges.cn");
                        Intent it = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(it);
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "取消访问", Toast.LENGTH_SHORT).show();
                    }
                }, getActivity().getFragmentManager());
                break;
            case R.id.rl_partner:
            case R.id.tv_partner:
            case R.id.iv_partner_more:
                TwoButtonDialog partner = new TwoButtonDialog();
                partner.show("您即将退出应用程序，前往S&W官网", "确定？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri uri = Uri.parse("http://www.thethreestooges.cn");
                        Intent it = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(it);
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "取消访问", Toast.LENGTH_SHORT).show();
                    }
                }, getActivity().getFragmentManager());
                break;
            case R.id.tv_address:
            case R.id.iv_address_more:
                Intent address = new Intent(getActivity(), AddressActivity.class);
                getActivity().startActivity(address);
                break;
        }
    }
}
