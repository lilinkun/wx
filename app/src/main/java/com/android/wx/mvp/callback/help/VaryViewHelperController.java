package com.android.wx.mvp.callback.help;

import android.view.View;


public class VaryViewHelperController {

    private IVaryViewHelper varyViewHelper;

    public VaryViewHelperController(View view) {
        this.varyViewHelper = new VaryViewHelper(view);
    }


    public void disLoading() {
        varyViewHelper.disLoading();
    }


    /**
     * 恢复到之前的视图
     */
    public void restore() {
        varyViewHelper.restoreView();
    }
}
