package com.android.wx.base.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.android.wx.event.EventCenter;
import com.android.wx.mvp.callback.help.VaryViewHelperController;

import androidx.appcompat.app.AppCompatActivity;
import de.greenrobot.event.EventBus;

/**
 * Created by kai
 * on 2021/1/13
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected VaryViewHelperController varyViewHelperController;
    protected Bundle savedInstanceState;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        if (null != getLoadingTargeView()) {
            varyViewHelperController = new VaryViewHelperController(getLoadingTargeView());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        if (isRegisterEventBus() && !EventBus.getDefault().isRegistered(this)) {
            if (isRegisterEventBusForSticky()) {
                EventBus.getDefault().registerSticky(this);
            } else {
                EventBus.getDefault().register(this);
            }
        }

        if (getContentViewLayoutId() > 0) {
            if (isFullScreen()) {
                // 隐藏android系统的状态栏
                this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
            setContentView(getContentViewLayoutId());
        } else {
            throw new IllegalArgumentException("you contentview is null ");
        }
        initData();
    }

    public void onEventMainThread(EventCenter eventCenter) {
        if (null != eventCenter) {
            onReceiverEvent(eventCenter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isRegisterEventBus() && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    public abstract View getLoadingTargeView();

    public boolean isRegisterEventBus() {
        return true;
    }

    public abstract int getContentViewLayoutId();

    /**
     * 是否为全屏
     *
     * @return
     */
    public boolean isFullScreen() {
        return true;
    }

    public boolean isRegisterEventBusForSticky() {
        return false;
    }

    /**
     * 接收EventBus发送的事件
     *
     * @param eventCenter
     */
    protected abstract void onReceiverEvent(EventCenter eventCenter);
    public abstract void initData();
}
