package com.zhiying.zylibrary.okhttpzy;

/**
 * 时间    2019-05-27 17:03
 * 描述
 */
interface ProgressCallback {
    void updateProgress(int progress, long networkSpeed, boolean done);
}
