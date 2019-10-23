package com.zhiying.zhiyinglibrary.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.zhiying.zhiyinglibrary.R;
import com.zhiying.zylibrary.activity.ZYBaseWebViewActivity;

import butterknife.InjectView;

/**
 * test agentweb
 *
 * @author ZY
 */
public class ZYWebviewActivity extends ZYBaseWebViewActivity {

    @InjectView(R.id.ll_webview)
    LinearLayout llWebview;

    @Override
    protected void onPageFinish(WebView view, String url) {

    }

    @Override
    protected void onPageStart(WebView view, String url, Bitmap favicon) {

    }

    @Override
    protected void loadUrlProgress(WebView view, int newProgress) {

    }

    @Override
    protected LinearLayout ShowPosition() {
        return llWebview;
    }

    @Override
    protected String loadWebUrl() {
        return "https://github.com";
    }

    @Override
    protected void initWebView() {

    }

    @Override
    protected void getWebTitle(String title) {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected void initDatasAndEvents() {

    }

    @Override
    protected int initView() {
        return R.layout.activity_zywebview;
    }

    @Override
    protected String setBaseTitle() {
        return "GitHub";
    }

}
