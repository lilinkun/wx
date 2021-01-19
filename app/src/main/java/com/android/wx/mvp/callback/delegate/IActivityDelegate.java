package com.android.wx.mvp.callback.delegate;

import android.os.Bundle;

import com.android.wx.mvp.callback.presenter.IPresenter;
import com.android.wx.mvp.callback.view.IView;

/**
 * Created by kai
 * on 2021/1/13
 */
public interface IActivityDelegate<V extends IView, P extends IPresenter> {
    void onCreate(Bundle bundle);

    void onDestroy();


    void onPause();


    void onResume();


    void onStart();


    void onStop();


    void onRestart();


    void onContentChanged();


    void onSaveInstanceState(Bundle outState);


    void onPostCreate(Bundle savedInstanceState);
}
