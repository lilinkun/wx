package com.android.wx.view;

import com.android.wx.base.BaseView;

/**
 * Created by kai
 * on 2021/1/13
 */
public interface UserInfoView extends BaseView {
    public void onLoginSucceed(Long userId);
    public void onLoginFailed();
}
