package com.zhiying.zylibrary.viewutil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zhiying.zylibrary.R;

/**
 * 自定义居中弹出dialog
 * 使用注意 show之后绑定控件去获取控件内的信息  否则报空
 * dialog.show();
 * @author ZY
 */
public class ZYCenterDialogActivity extends AppCompatDialog implements View.OnClickListener {

    private Context mContext;
    /**
     * 布局ID
     */
    private int layoutID;
    /**
     * 对应控件的ID集合
     */
    private int[] listeningItem;
    private OnCenterItemClickListener onCenterItemClickListener;


    /**
     * 初始化弹出窗口视图
     *
     * @param context
     * @param layoutID      对应的布局
     * @param listeningItem 对应的id进行添加点击事件
     */
    public ZYCenterDialogActivity(Context context, int layoutID, int[] listeningItem) {
        super(context, R.style.zyDialog_custom);
        mContext = context;
        this.layoutID = layoutID;
        this.listeningItem = listeningItem;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        //set position centered
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.zyBottom_menu_animation);
        setContentView(layoutID);
        // 宽度全屏
        WindowManager windowManager = ((Activity) mContext).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth() * 19 / 20; // 设置dialog宽度为屏幕的19 / 20
        getWindow().setAttributes(lp);
        // 点击Dialog外部消失
        setCanceledOnTouchOutside(true);
        for (int id : listeningItem) {
            findViewById(id).setOnClickListener(this);
        }
    }

    /**
     * 设置点击事件监听
     *
     * @param onCenterItemClickListener
     */
    public void setOnCenterItemClickListener(OnCenterItemClickListener onCenterItemClickListener) {
        this.onCenterItemClickListener = onCenterItemClickListener;
    }

    @Override
    public void onClick(View view) {
        onCenterItemClickListener.OnCenterItemClick(this, view);
    }

    public interface OnCenterItemClickListener {
        void OnCenterItemClick(ZYCenterDialogActivity dialog, View view);
    }

}
