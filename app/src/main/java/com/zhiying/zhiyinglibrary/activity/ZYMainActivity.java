package com.zhiying.zhiyinglibrary.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zhiying.zhiyinglibrary.R;
import com.zhiying.zylibrary.activity.ZYBaseActivity;
import com.zhiying.zylibrary.easythread.Callback;
import com.zhiying.zylibrary.easythread.EasyThread;
import com.zhiying.zylibrary.utils.statusbar.AlexStatusBarUtils;
import com.zhiying.zylibrary.viewutil.ZYCenterDialogActivity;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author ZY
 */
public class ZYMainActivity extends ZYBaseActivity {


    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_beautifulpicture)
    TextView tvBeautifulpicture;

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected void initDatasAndEvents() {

        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        AlexStatusBarUtils.setRootViewFitsSystemWindows(this, true);
        //设置状态栏透明
        AlexStatusBarUtils.setTranslucentStatus(this);
        //设置状态使用深色文字图标风格
        if (AlexStatusBarUtils.setStatusBarDarkTheme(this, false)) {
            //不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            AlexStatusBarUtils.setStatusBarColor(this, R.color.colorPrimary);
        }

        //单一线程 测试
        //------------------Start
        EasyThread easyThread = EasyThread.Builder.createSingle().setPriority(Thread.MAX_PRIORITY).setCallback(new Callback() {
            @Override
            public void onError(String threadName, Throwable t) {
                Log.e("--hh", "onError: ");
            }

            @Override
            public void onCompleted(String threadName) {
                Log.e("--hh", "onCompleted: " + threadName);
            }

            @Override
            public void onStart(String threadName) {
                Log.e("--hh", "onStart: " + threadName);
            }
        }).setName("Runnable task").build();

        easyThread.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    Log.e("--hh", System.currentTimeMillis() + " run: Go Go Go.............");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Future<String> future = easyThread.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return System.currentTimeMillis() + " Come On,Go Go Go.";
            }
        });

        try {
            String str = future.get();
            Log.e("--hh"    , "initDatasAndEvents: " + str);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //------------------End


    }

    @Override
    protected int initView() {
        return R.layout.activity_zymain;
    }

    @Override
    protected String setBaseTitle() {
        return "主页";
    }

    @OnClick({R.id.tv_name, R.id.tv_beautifulpicture})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_name:
                //弹出框测试
                final ZYCenterDialogActivity zyCenterDialogActivity = new ZYCenterDialogActivity(mContext, R.layout
                        .zydialog_testone, new int[]{R.id.btn_another});
                zyCenterDialogActivity.show();
                zyCenterDialogActivity.setOnCenterItemClickListener(new ZYCenterDialogActivity
                        .OnCenterItemClickListener() {
                    @Override
                    public void OnCenterItemClick(ZYCenterDialogActivity dialog, View view) {
                        if (view.getId() == R.id.btn_another) {
                            showLToast("没得啦！");
                            zyCenterDialogActivity.dismiss();
                        }
                    }
                });
                break;
            case R.id.tv_beautifulpicture:
                readyGo(ZYWebviewActivity.class);
                break;
            default:
        }
    }
}
