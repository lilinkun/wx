package com.android.wx.presenter;

import android.content.Context;

import com.android.wx.db.DBManager;
import com.android.wx.event.EventCenter;
import com.android.wx.model.UserInfo;
import com.android.wx.mvp.callback.presenter.impl.BasePresenter;
import com.android.wx.contract.UserInfoView;

import java.util.List;

/**
 * Created by kai
 * on 2021/1/13
 */
public class UserInfoPresenter extends BasePresenter<UserInfoView> {
    @Override
    public void onReceiverEvent(EventCenter eventCenter) {

    }

    public void install(Context context) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("admin");
        userInfo.setPermission(1);
        userInfo.setPwd(123456);

        DBManager.getInstance(context).insertUserInfo(userInfo);

    }

    public void query(Context context,String pwd) {
//        List<UserInfo> list = mDaoSession.getUserInfoDao().queryBuilder().list();
        List<UserInfo> list = DBManager.getInstance(context).queryUserInfo(pwd);
        if (list.size() > 0) {
            getView().onLoginSucceed(list.get(0).getId());
        }else {
            getView().onLoginFailed();
        }

    }
}
