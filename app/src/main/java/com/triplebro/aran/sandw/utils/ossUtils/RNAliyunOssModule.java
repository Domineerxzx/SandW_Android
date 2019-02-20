package com.triplebro.aran.sandw.utils.ossUtils;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;


/**
 * Created by Aran on 2018/6/10.
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * <p>
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 */

public class RNAliyunOssModule extends ReactContextBaseJavaModule {
    public RNAliyunOssModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "RNAliyunOSS";
    }
    @ReactMethod
    public void initWithServerSTS(final String server, String endPoint, ReadableMap configuration) {
        /*这里写调用安卓代码授权的逻辑*/

    }
    @ReactMethod
    public void asyncUpload(String bucketName, String ossFile, String sourceFile,ReadableMap options, final Promise promise) {
        /**这里写调用安卓代码上传的逻辑*/
    }
}
