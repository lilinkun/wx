package com.android.wx.mvp.callback.presenter.impl;

import android.database.sqlite.SQLiteDatabase;

import com.android.wx.base.MyApplication;
import com.android.wx.event.EventCenter;
import com.android.wx.mvp.callback.presenter.IPresenter;
import com.android.wx.mvp.callback.view.IView;

import java.lang.ref.WeakReference;

import de.greenrobot.event.EventBus;

/**
 * Created by kai
 * on 2021/1/13
 */
public abstract class BasePresenter<V extends IView> implements IPresenter<V> {
    private WeakReference<V> viewRef;

    @Override
    public void attachView(V view) {
        viewRef = new WeakReference<V>(view);
        initGreenDao();
        if (!EventBus.getDefault().isRegistered(this)) {
            if (isRegisterEventbusForSticky()) {
                EventBus.getDefault().registerSticky(this);
            } else {
                EventBus.getDefault().register(this);
            }
        }

    }

    @Override
    public void detachView(boolean retainInstance) {

        if (null != viewRef) {
            viewRef.clear();
            viewRef = null;
        }
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }

    }

    @Override
    public V getView() {
        return viewRef == null ? null : viewRef.get();
    }

    @Override
    public boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    /**
     * 是否将事件总线设置为粘性事件,此方法生效的前提是必须为注册事件总线，即isRegisterEventBus的返回值为true
     *
     * @return true 表示将事件总线注册为粘性，false 则注册为一般事件总线
     */
    public boolean isRegisterEventbusForSticky() {

        return false;
    }

    public void onEventMainThread(EventCenter eventCenter) {

        if (null != eventCenter) {
            onReceiverEvent(eventCenter);
        }
    }

    public abstract void onReceiverEvent(EventCenter eventCenter);


    private void initGreenDao() {
    }
}
