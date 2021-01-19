package com.android.wx.mvp.callback.delegate.impl;

import android.os.Bundle;

import com.android.wx.mvp.callback.IDelegateCallback;
import com.android.wx.mvp.callback.delegate.IActivityDelegate;
import com.android.wx.mvp.callback.presenter.IPresenter;
import com.android.wx.mvp.callback.view.IView;

/**
 * Created by kai
 * on 2021/1/13
 */
public class ActivityDelegate<V extends IView, P extends IPresenter> implements IActivityDelegate<V, P> {
    private IDelegateCallback<V, P> delegateCallback;

    public ActivityDelegate(IDelegateCallback<V, P> delegateCallback) {
        if (null == delegateCallback) {
            throw new NullPointerException("IDelegateCallback is null");
        } else {
            this.delegateCallback = delegateCallback;
        }


    }

    /**
     * 同Activity生命周期方法onCreate()
     *
     * @param bundle
     */
    @Override
    public void onCreate(Bundle bundle) {

        P presenter = delegateCallback.getPresenter();//通过注册的接口回调获取当前泛型类型的presenter
        if (null == presenter) { //当泛型为null时，通过接口回调获取presenter,故应在createPresenter()中创建相应类型的presenter
            presenter = delegateCallback.createPresenter();
        }

        if (presenter == null) {//没有创建presenter则抛出异常
            throw new NullPointerException("Presenter is null! Do you return null in createPresenter()?");
        }

        delegateCallback.setPresenter(presenter);//通过接口回调将presenter赋值给当前Activity

        delegateCallback.getPresenter().attachView(delegateCallback.getMvpView());//将presenter与Activity(Iview的实现类)绑定
    }

    /**
     * 同Activity生命周期的onDestroy()方法
     */
    @Override
    public void onDestroy() {
        delegateCallback.getPresenter().detachView(delegateCallback.shouldInstanceBeRetained());//将presenter与Activity(Iview的实现类)解除绑定
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
    public void onRestart() {

    }

    @Override
    public void onContentChanged() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {

    }
}

