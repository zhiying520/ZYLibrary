package com.zhiying.zylibrary.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.zhiying.zylibrary.R;


/**
 * agentweb view load url
 *
 * @author ZY
 */
public abstract class ZYBaseWebViewActivity extends ZYBaseActivity {

    public AgentWeb awBaseWebview;
    /**
     * 获取title
     */
    WebChromeClient mCallback = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            loadUrlProgress(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
//            super.onReceivedTitle(view, title);
            getWebTitle(title);
        }
    };
    //WebViewClient 用于监听界面的开始和结束
    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //界面开始
            onPageStart(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            //界面结束
            onPageFinish(view, url);
        }
    };
    //WebChromeClient 监听界面的改变
    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //界面改变
        }
    };

    /**
     * page finish
     *
     * @param view
     * @param url
     */
    protected abstract void onPageFinish(WebView view, String url);

    /**
     * page start
     *
     * @param view
     * @param url
     * @param favicon
     */
    protected abstract void onPageStart(WebView view, String url, Bitmap favicon);

    /**
     * current url loading progress
     *
     * @param view
     * @param newProgress
     */
    protected abstract void loadUrlProgress(WebView view, int newProgress);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        awBaseWebview = AgentWeb.with(mActivity)//传入Activity
                .setAgentWebParent(ShowPosition(), new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为
                .useDefaultIndicator(R.color.zy_lightgrey)// 使用默认进度条
                .setWebViewClient(mWebViewClient)
                .setWebChromeClient(mCallback) //设置 Web 页面的 title 回调
                .setWebChromeClient(mWebChromeClient)
                .createAgentWeb()
                .ready()
                .go(loadWebUrl());
        initWebView();
    }

    /**
     * display position should be linearLayout
     *
     * @return
     */
    protected abstract LinearLayout ShowPosition();

    /**
     * load url
     *
     * @return
     */
    protected abstract String loadWebUrl();

    /**
     * you can init your data
     */
    protected abstract void initWebView();

    /**
     * get web title
     *
     * @param title
     */
    protected abstract void getWebTitle(String title);

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (awBaseWebview.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 跟随生命周期释放资源更省电
     */
    @Override
    protected void onPause() {
        awBaseWebview.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        awBaseWebview.getWebLifeCycle().onResume();
        super.onResume();
    }

}
