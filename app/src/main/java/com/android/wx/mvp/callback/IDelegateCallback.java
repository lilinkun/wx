package com.android.wx.mvp.callback;

import com.android.wx.mvp.callback.presenter.IPresenter;
import com.android.wx.mvp.callback.view.IView;

import androidx.annotation.NonNull;

/**
 * Created by kai
 * on 2021/1/13
 */
public interface IDelegateCallback<V extends IView, P extends IPresenter> {
    /**
     * 创建一个实现了IPresenter的泛型presenter
     *
     * @return
     */
    @NonNull
    public P createPresenter();

    /**
     * 获取实现了IPresenter的泛型presenter
     *
     * @return
     */
    public P getPresenter();

    /**
     * 设置Presenter
     *
     * @param presenter
     */
    public void setPresenter(P presenter);

    /**
     * 获取实现了IView的泛型视图
     *
     * @return
     */
    public V getMvpView();

    public boolean isRetainInstance();


    public void setRetainInstance(boolean retainingInstance);


    public boolean shouldInstanceBeRetained();
}
