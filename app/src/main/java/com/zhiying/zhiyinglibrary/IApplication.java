package com.zhiying.zhiyinglibrary;

import android.app.Application;

import com.zhiying.zylibrary.okhttpzy.Constants;
import com.zhiying.zylibrary.okhttpzy.OkHttpParameter;
import com.zhiying.zylibrary.okhttpzy.OkHttpParameterConfiguration;
import com.zhiying.zylibrary.okhttpzy.Part;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;
import okhttp3.Interceptor;

/**
 * 作者    Created by ZY
 * 时间    2019-05-28 14:23
 * 文件    ZhiYingLibrary
 * 描述
 */
public class IApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化 HttpRequest请求参数
        List<Part> commomParams = new ArrayList<>();
        Headers commonHeaders = new Headers.Builder().build();
        List<Interceptor> interceptorList = new ArrayList<>();
        OkHttpParameterConfiguration.Builder builder = new OkHttpParameterConfiguration.Builder()
                .setCommenParams(commomParams)
                .setCommenHeaders(commonHeaders)
                .setTimeout(Constants.REQ_TIMEOUT)
                .setInterceptors(interceptorList)
                .setDebug(true);
        OkHttpParameter.getInstance().init(builder.build());

    }
}
