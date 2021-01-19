package com.android.wx.mvp.callback.delegate.impl;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.android.wx.mvp.callback.IDelegateCallback;
import com.android.wx.mvp.callback.delegate.IFragmentDellegate;
import com.android.wx.mvp.callback.presenter.IPresenter;
import com.android.wx.mvp.callback.view.IView;

import androidx.annotation.Nullable;

/**
 * Created by kai
 * on 2021/1/13
 */
public class FragmentDelegate<V extends IView, P extends IPresenter> implements IFragmentDellegate<V, P> {
    private IDelegateCallback<V, P> delegateCallback;

    public FragmentDelegate(IDelegateCallback<V, P> delegateCallback) {

        if (null == delegateCallback) {
            throw new NullPointerException("IDelegateCallback is null");
        }

        this.delegateCallback = delegateCallback;
    }

    @Override
    public void onCreate(Bundle saved) {


    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        P presenter = delegateCallback.getPresenter();

        if (null == presenter) {
            presenter = delegateCallback.createPresenter();
        }

        if (null == presenter) {
            throw new NullPointerException("Presenter is null! Do you return null in createPresenter()?");
        }

        delegateCallback.setPresenter(presenter);
        delegateCallback.getPresenter().attachView(delegateCallback.getMvpView());
    }

    @Override
    public void onDestroyView() {

        delegateCallback.getPresenter().detachView(delegateCallback.shouldInstanceBeRetained());
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

    }

    @Override
    public void onAttach(Activity activity) {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }
}
