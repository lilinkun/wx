package com.android.wx.mvp.callback;

import com.android.wx.mvp.callback.presenter.IPresenter;
import com.android.wx.mvp.callback.view.IView;

import androidx.fragment.app.FragmentActivity;

/**
 * Created by kai
 * on 2021/1/13
 */
public interface IActivityDelegateCallback<V extends IView, P extends IPresenter> extends IDelegateCallback<V, P> {
    public FragmentActivity getActivity();
}
