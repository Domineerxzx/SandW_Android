package com.triplebro.aran.sandw.fragmentReact;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.shell.MainReactPackage;
import com.triplebro.aran.sandw.BuildConfig;
import com.triplebro.aran.sandw.R;
import com.triplebro.aran.sandw.modules.AransPackage;

/**
 * Created by Aran on 2018/6/27.
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * <p>
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 */

public class ReactBrandFragment extends Fragment{
    ReactRootView mReactRootView;
    ReactInstanceManager mReactInstanceManager;
    private TextView tv_title;
    private AransPackage reactPackage = new AransPackage();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mReactRootView = new ReactRootView(getActivity());
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getActivity().getApplication())
                .setBundleAssetName("index.android.bundle")
                .setJSMainModulePath("index")
                .addPackage(new MainReactPackage())
                .addPackage(reactPackage)
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();
        mReactRootView.startReactApplication(mReactInstanceManager, "SandW_BR", null);
        initView();
        initData();
        return mReactRootView;
    }

    public AransPackage getReactPackage() {
        return reactPackage;
    }

    public void setReactPackage(AransPackage reactPackage) {
        this.reactPackage = reactPackage;
    }

    private void initData() {

    }

    private void initView() {
        tv_title = getActivity().findViewById(R.id.tv_title);
    }
}
