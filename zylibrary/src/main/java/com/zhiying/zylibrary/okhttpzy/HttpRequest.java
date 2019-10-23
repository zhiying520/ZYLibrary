/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.zhiying.zylibrary.okhttpzy;

import android.widget.Toast;

import com.zhiying.zylibrary.utils.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * http请求类
 * 1、在你App Application中初始化OkHttpFinal(此初始化只是简单赋值不会阻塞线程)
 *   OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
 *   OkHttpFinal.getInstance().init(builder.build());
 */
public final class HttpRequest {

    /*一个对OkHttp封装的简单易用型HTTP请求和文件下载管理框架。更多详情请查看WIKI

            简化OkHttp使用
    支持GET,POST,PUT,DELETE,HEAD,PATCH谓词
            支持Activity和Fragment生命周期结束后终止请求
    支持Download Manager功能
    支持文件下载多事件回调
            支持返回bean对象
    支持返回json String数据
    支持返回JsonObject对象
            支持https请求
    支持https证书访问
            支持文件上传
    支持全局params
            支持全局header
    支持http cancel*/

    /*List<File> files = new ArrayList<>();
    File file = new File("...");
    RequestParams params = new RequestParams(this);//请求参数
    params.addFormDataPart("username", mUserName);//表单数据
    params.addFormDataPart("password", mPassword);//表单数据
    params.addFormDataPart("file", file);//上传单个文件
    params.addFormDataPart("files", files);//上传多个文件
    params.addHeader("token", token);//添加header信息
    HttpRequest.post(Api.LOGIN, params, new BaseHttpRequestCallback<LoginResponse>() {

    //请求网络前
    @Override
    public void onStart() {
        buildProgressDialog().show();
    }

    //这里只是网络请求成功了（也就是服务器返回JSON合法）没有没有分装具体的业务成功与失败，大家可以参考demo去分装自己公司业务请求成功与失败
    @Override
    protected void onSuccess(LoginResponse loginResponse) {
        toast(loginResponse.getMsg());
    }

    //请求失败（服务返回非法JSON、服务器异常、网络异常）
    @Override
    public void onFailure(int errorCode, String msg) {
        toast("网络异常~，请检查你的网络是否连接后再试");
    }

    //请求网络结束
    @Override
    public void onFinish() {
        dismissProgressDialog();
    }*/

    /*String url = "http://";
    File saveFile = new File("/sdcard/.apk");
    HttpRequest.download(url, saveFile, new FileDownloadCallback() {
        //开始下载
        @Override
        public void onStart() {
            super.onStart();
        }

        //下载进度
        @Override
        public void onProgress(int progress, long networkSpeed) {
            super.onProgress(progress, networkSpeed);
            mPbDownload.setProgress(progress);
            //String speed = FileUtils.generateFileSize(networkSpeed);
        }

        //下载失败
        @Override
        public void onFailure() {
            super.onFailure();
            Toast.makeText(getBaseContext(), "下载失败", Toast.LENGTH_SHORT).show();
        }

        //下载完成（下载成功）
        @Override
        public void onDone() {
            super.onDone();
            Toast.makeText(getBaseContext(), "下载成功", Toast.LENGTH_SHORT).show();
        }
    });*/

    public static void get(String url) {
        get(url, null, null);
    }

    public static void get(String url, RequestParams params) {
        get(url, params, null);
    }

    public static void get(String url, BaseHttpRequestCallback callback) {
        get(url, null, callback);
    }

    /**
     * Get请求
     * @param url
     * @param params
     * @param callback
     */
    public static void get(String url, RequestParams params, BaseHttpRequestCallback callback) {
        get(url, params, Constants.REQ_TIMEOUT, callback);
    }

    public static void get(String url, RequestParams params, long timeout, BaseHttpRequestCallback callback) {
        OkHttpClient.Builder builder = OkHttpParameter.getInstance().getOkHttpClientBuilder();
        builder.readTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.connectTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.writeTimeout(timeout, TimeUnit.MILLISECONDS);
        executeRequest(Method.GET, url, params, builder, callback);
    }

    public static void get(String url, RequestParams params, OkHttpClient.Builder builder, BaseHttpRequestCallback callback) {
        executeRequest(Method.GET, url, params, builder, callback);
    }

    public static void post(String url) {
        post(url, null, null);
    }

    public static void post(String url, RequestParams params) {
        post(url, params, null);
    }

    public static void post(String url, BaseHttpRequestCallback callback) {
        post(url, null, callback);
    }

    /**
     * Post请求
     * @param url
     * @param params
     * @param callback
     */
    public static void post(String url, RequestParams params, BaseHttpRequestCallback callback) {
        post(url, params, Constants.REQ_TIMEOUT, callback);
    }

    public static void post(String url, RequestParams params, long timeout, BaseHttpRequestCallback callback) {
        OkHttpClient.Builder builder = OkHttpParameter.getInstance().getOkHttpClientBuilder();
        builder.readTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.connectTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.writeTimeout(timeout, TimeUnit.MILLISECONDS);
        executeRequest(Method.POST, url, params, builder, callback);
    }

    public static void post(String url, RequestParams params, OkHttpClient.Builder builder, BaseHttpRequestCallback callback) {
        executeRequest(Method.POST, url, params, builder, callback);
    }

    public static void put(String url) {
        put(url, null, null);
    }

    public static void put(String url, RequestParams params) {
        put(url, params, null);
    }

    public static void put(String url, BaseHttpRequestCallback callback) {
        put(url, null, callback);
    }

    /**
     * put请求
     * @param url
     * @param params
     * @param callback
     */
    public static void put(String url, RequestParams params, BaseHttpRequestCallback callback) {
        put(url, params, Constants.REQ_TIMEOUT, callback);
    }

    public static void put(String url, RequestParams params, long timeout, BaseHttpRequestCallback callback) {
        OkHttpClient.Builder builder = OkHttpParameter.getInstance().getOkHttpClientBuilder();
        builder.readTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.connectTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.writeTimeout(timeout, TimeUnit.MILLISECONDS);
        executeRequest(Method.PUT, url, params, builder, callback);
    }

    public static void put(String url, RequestParams params, OkHttpClient.Builder builder, BaseHttpRequestCallback callback) {
        executeRequest(Method.PUT, url, params, builder, callback);
    }

    public static void delete(String url) {
        delete(url, null, null);
    }

    public static void delete(String url, RequestParams params) {
        delete(url, params, null);
    }

    public static void delete(String url, BaseHttpRequestCallback callback) {
        delete(url, null, callback);
    }

    /**
     * delete请求
     * @param url
     * @param params
     * @param callback
     */
    public static void delete(String url, RequestParams params, BaseHttpRequestCallback callback) {
        delete(url, params, Constants.REQ_TIMEOUT, callback);
    }

    public static void delete(String url, RequestParams params, long timeout, BaseHttpRequestCallback callback) {
        OkHttpClient.Builder builder = OkHttpParameter.getInstance().getOkHttpClientBuilder();
        builder.readTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.connectTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.writeTimeout(timeout, TimeUnit.MILLISECONDS);
        executeRequest(Method.DELETE, url, params, builder, callback);
    }

    public static void delete(String url, RequestParams params, OkHttpClient.Builder builder, BaseHttpRequestCallback callback) {
        executeRequest(Method.DELETE, url, params, builder, callback);
    }

    public static void head(String url) {
        head(url, null, null);
    }

    public static void head(String url, RequestParams params) {
        head(url, params, null);
    }

    public static void head(String url, BaseHttpRequestCallback callback) {
        head(url, null, callback);
    }

    /**
     * head请求
     * @param url
     * @param params
     * @param callback
     */
    public static void head(String url, RequestParams params, BaseHttpRequestCallback callback) {
        head(url, params, Constants.REQ_TIMEOUT, callback);
    }

    public static void head(String url, RequestParams params, long timeout, BaseHttpRequestCallback callback) {
        OkHttpClient.Builder builder = OkHttpParameter.getInstance().getOkHttpClientBuilder();
        builder.readTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.connectTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.writeTimeout(timeout, TimeUnit.MILLISECONDS);
        executeRequest(Method.HEAD, url, params, builder, callback);
    }

    public static void head(String url, RequestParams params, OkHttpClient.Builder builder, BaseHttpRequestCallback callback) {
        executeRequest(Method.HEAD, url, params, builder, callback);
    }

    public static void patch(String url) {
        patch(url, null, null);
    }

    public static void patch(String url, RequestParams params) {
        patch(url, params, null);
    }

    public static void patch(String url, BaseHttpRequestCallback callback) {
        patch(url, null, callback);
    }

    /**
     * patch请求
     * @param url
     * @param params
     * @param callback
     */
    public static void patch(String url, RequestParams params, BaseHttpRequestCallback callback) {
        patch(url, params, Constants.REQ_TIMEOUT, callback);
    }

    public static void patch(String url, RequestParams params, long timeout, BaseHttpRequestCallback callback) {
        OkHttpClient.Builder builder = OkHttpParameter.getInstance().getOkHttpClientBuilder();
        builder.readTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.connectTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.writeTimeout(timeout, TimeUnit.MILLISECONDS);
        executeRequest(Method.PATCH, url, params, builder, callback);
    }

    public static void patch(String url, RequestParams params, OkHttpClient.Builder builder, BaseHttpRequestCallback callback) {
        executeRequest(Method.PATCH, url, params, builder, callback);
    }

    /**
     * 取消请求
     * @param url
     */
    public static void cancel(String url) {
        if ( !StringUtils.isEmpty(url) ) {
            Call call = OkHttpCallManager.getInstance().getCall(url);
            if ( call != null ) {
                call.cancel();
            }

            OkHttpCallManager.getInstance().removeCall(url);
        }
    }

    public static void download(String url, File target) {
        download(url, target, null);
    }

    /**
     * 下载文件
     * @param url
     * @param target 保存的文件
     * @param callback
     */
    public static void download(String url, File target, FileDownloadCallback callback) {
        if (!StringUtils.isEmpty(url) && target != null) {
            FileDownloadTask task = new FileDownloadTask(url, target, callback);
            task.execute();
        }
    }

    private static void executeRequest(Method method, String url, RequestParams params, OkHttpClient.Builder builder, BaseHttpRequestCallback callback) {
        if (!StringUtils.isEmpty(url)) {
            if(builder == null) {
                builder = OkHttpParameter.getInstance().getOkHttpClientBuilder();
            }
            OkHttpTask task = new OkHttpTask(method, url, params, builder, callback);
            task.execute();
        }
    }

}
