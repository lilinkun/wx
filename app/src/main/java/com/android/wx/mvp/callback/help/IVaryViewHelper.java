package com.android.wx.mvp.callback.help;

import android.view.View;


public interface IVaryViewHelper {


    /**
     * 恢复View到改变之前的状态
     */
    void restoreView();

    /**
     * 在父布局上显示View
     *
     * @param view 需要显示的View
     */
    void showLayout(View view);

    void showLoading(View view);

    void disLoading();

    /**
     * 通过布局资源ID加载View
     *
     * @param layoutId 需要加载的布局资源ID
     * @return
     */
    View inflate(int layoutId);


    /**
     * 获取初始化时传入的View
     *
     * @return
     */
    View getView();

    /**
     * 获取当前显示的View
     *
     * @return
     */
    View getCurrentView();
}
