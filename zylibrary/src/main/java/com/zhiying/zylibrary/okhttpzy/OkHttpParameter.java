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

import android.text.TextUtils;

import com.zhiying.zylibrary.okhttpzy.https.HttpsCerManager;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;

import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.OkHttpClient;

/**
 * 时间    2019-05-27 15:21
 * 描述
 */
public class OkHttpParameter {

    private OkHttpClient okHttpClient;

    private static OkHttpParameter okHttpParameter;
    private OkHttpParameterConfiguration configuration;

    private OkHttpParameter() {
    }

    public synchronized void init(OkHttpParameterConfiguration configuration) {
        this.configuration = configuration;

        long timeout = configuration.getTimeout();
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.MILLISECONDS)
                .writeTimeout(timeout, TimeUnit.MILLISECONDS)
                .readTimeout(timeout, TimeUnit.MILLISECONDS);
        if ( configuration.getHostnameVerifier() != null ) {
            builder.hostnameVerifier(configuration.getHostnameVerifier());
        }

        List<InputStream> certificateList = configuration.getCertificateList();
        if (certificateList != null && certificateList.size() > 0) {
            HttpsCerManager httpsCerManager = new HttpsCerManager(builder);
            httpsCerManager.setCertificates(certificateList);
        }

        CookieJar cookieJar = configuration.getCookieJar();
        if (cookieJar != null) {
            builder.cookieJar(cookieJar);
        }

        if(configuration.getCache() != null) {
            builder.cache(configuration.getCache());
        }

        if (configuration.getAuthenticator() != null){
            builder.authenticator(configuration.getAuthenticator());
        }
        if (configuration.getCertificatePinner() != null) {
            builder.certificatePinner(configuration.getCertificatePinner());
        }
        builder.followRedirects(configuration.isFollowRedirects());
        builder.followSslRedirects(configuration.isFollowSslRedirects());
        if(configuration.getSslSocketFactory() != null) {
            builder.sslSocketFactory(configuration.getSslSocketFactory());
        }
        if(configuration.getDispatcher() != null) {
            builder.dispatcher(configuration.getDispatcher());
        }
        builder.retryOnConnectionFailure(configuration.isRetryOnConnectionFailure());
        if (configuration.getNetworkInterceptorList() != null) {
            builder.networkInterceptors().addAll(configuration.getNetworkInterceptorList());
        }
        if (configuration.getInterceptorList() != null) {
            builder.interceptors().addAll(configuration.getInterceptorList());
        }

        if(configuration.getProxy() != null){
            builder.proxy(configuration.getProxy());
        }
        ILogger.DEBUG = configuration.isDebug();
        ILogger.d("OkHttpParameter init...");
        Constants.DEBUG = configuration.isDebug();

        okHttpClient = builder.build();

    }

    public static OkHttpParameter getInstance() {
        if (okHttpParameter == null) {
            okHttpParameter = new OkHttpParameter();
        }
        return okHttpParameter;
    }

    /**
     * 修改公共请求参数信息
     * @param key
     * @param value
     */
    public void updateCommonParams(String key, String value) {
        boolean add = false;
        List<Part> commonParams = configuration.getCommonParams();
        if (commonParams != null){
            for (Part param:commonParams) {
                if (param != null && TextUtils.equals(param.getKey(), key)){
                    param.setValue(value);
                    add = true;
                    break;
                }
            }
        }
        if (!add) {
            commonParams.add(new Part(key, value));
        }
    }

    /**
     * 修改公共header信息
     * @param key
     * @param value
     */
    public void updateCommonHeader(String key, String value) {
        Headers headers = configuration.getCommonHeaders();
        if ( headers == null){
            headers = new Headers.Builder().build();
        }
        configuration.commonHeaders = headers.newBuilder().set(key, value).build();
    }

    @Deprecated
    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public OkHttpClient.Builder getOkHttpClientBuilder() {
        return okHttpClient.newBuilder();
    }

    public List<Part> getCommonParams() {
        return configuration.getCommonParams();
    }

    public List<InputStream> getCertificateList() {
        return configuration.getCertificateList();
    }

    public HostnameVerifier getHostnameVerifier() {
        return configuration.getHostnameVerifier();
    }

    public long getTimeout() {
        return configuration.getTimeout();
    }

    public Headers getCommonHeaders() {
        return configuration.getCommonHeaders();
    }
}
