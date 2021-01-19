package com.android.wx.mvp.callback.presenter;

import com.android.wx.mvp.callback.view.IView;

/**
 * Created by kai
 * on 2021/1/13
 */
public interface IPresenter<V extends IView> {
    /**
     * 将Presenter与视图进行绑定
     * @param view 泛型，实现了IView的视图
     */
    public void attachView(V view) ;

    /**
     * 将Presenter与已经绑定视图解除绑定
     * @param retainInstance
     */
    public void detachView(boolean retainInstance);

    /**
     * 获取实现了IView接口的视图泛型
     * @return
     */
    public V getView();

    /**
     * Presenter是否与视图绑定，true已绑定，false为绑定
     * 当Presenter未绑定成功调用视图的方法将报错，故需要判断
     * @return
     */
    public boolean isViewAttached();
}
