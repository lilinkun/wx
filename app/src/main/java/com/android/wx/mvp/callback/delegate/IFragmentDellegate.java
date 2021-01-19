package com.android.wx.mvp.callback.delegate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.android.wx.mvp.callback.presenter.IPresenter;
import com.android.wx.mvp.callback.view.IView;

import androidx.annotation.Nullable;

/**
 * Created by kai
 * on 2021/1/13
 */
public interface IFragmentDellegate<V extends IView, P extends IPresenter> {
    public void onCreate(Bundle saved);

    public void onDestroy();


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState);


    public void onDestroyView();


    public void onPause();


    public void onResume();


    public void onStart();


    public void onStop();


    public void onActivityCreated(Bundle savedInstanceState);


    public void onAttach(Activity activity);


    public void onDetach();


    public void onSaveInstanceState(Bundle outState);
}
