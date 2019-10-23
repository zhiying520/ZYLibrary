package com.zhiying.zhiyinglibrary;

import android.os.Bundle;
import android.widget.TextView;

import com.zhiying.zylibrary.activity.ZYBaseActivity;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author ZY
 */
public class ZYMainActivity extends ZYBaseActivity {


    @InjectView(R.id.tv_name)
    TextView tvName;

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
        return R.layout.activity_zymain;
    }

    @Override
    protected String setBaseTitle() {
        return "主页";
    }


    @OnClick(R.id.tv_name)
    public void onViewClicked() {
    }
}
