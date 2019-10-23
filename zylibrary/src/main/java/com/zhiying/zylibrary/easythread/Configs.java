package com.zhiying.zylibrary.easythread;

import java.util.concurrent.Executor;

/**
 * Store some configurations for current task.
 */
final class Configs {
    String name;// thread name
    Callback callback;// thread callback
    long delay;// delay time
    Executor deliver;// thread deliver
    AsyncCallback asyncCallback;
}
