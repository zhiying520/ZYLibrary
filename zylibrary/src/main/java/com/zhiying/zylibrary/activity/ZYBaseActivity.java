package com.zhiying.zylibrary.activity;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhiying.zylibrary.R;

import butterknife.ButterKnife;

/**
 * base activity
 *
 * @author ZY
 */
public abstract class ZYBaseActivity extends ZYBaseAppCompatActivity {

    /**
     * base head view
     */
    public ConstraintLayout clZybasetop;
    /**
     * confirm button on the right
     */
    public TextView tvZybasesure;
    /**
     * add right button
     */
    public ImageView ivZybaseadd;
    /**
     * left back button
     */
    ImageView ivZybaseback;
    /**
     * title button
     */
    TextView tvZybasetitle;
    FrameLayout flZybasecontent;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        clZybasetop = findViewById(R.id.cl_zybasetop);
        ivZybaseback = findViewById(R.id.iv_zybaseback);
        tvZybasesure = findViewById(R.id.tv_zybasesure);
        tvZybasetitle = findViewById(R.id.tv_zybasetitle);
        ivZybaseadd = findViewById(R.id.iv_zybaseadd);
        flZybasecontent = findViewById(R.id.fl_zybasecontent);

        if (mContext != null) {
            flZybasecontent.addView(View.inflate(mContext, initView(), null));
        }

        /**
         * finish activity
         */
        if (ivZybaseback != null) {
            ivZybaseback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        tvZybasetitle.setText(setBaseTitle());

        ButterKnife.inject(this);
    }

    /**
     * initView
     *
     * @return add view
     */
    protected abstract int initView();

    /**
     * set basetitle positon
     *
     * @return basetitle
     */
    protected abstract String setBaseTitle();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_zybase;
    }

    /**
     * set basetitle position
     *
     * @param position
     */
    protected void setTitlePosition(int position) {
        if (tvZybasetitle != null) {
            tvZybasetitle.setGravity(position);
        }
    }

    /**
     * set BaseHead Visible
     *
     * @param isvisible
     */
    public void setBaseHeadVisible(int isvisible) {
        clZybasetop.setVisibility(isvisible);
    }
}
