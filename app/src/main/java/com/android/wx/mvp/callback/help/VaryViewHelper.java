package com.android.wx.mvp.callback.help;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.android.wx.base.MyApplication;


public class VaryViewHelper implements IVaryViewHelper {

    private View view;
    private ViewGroup parentView;
    private int viewIndex; //在父布局中的位置
    private ViewGroup.LayoutParams params;
    private View currentView;
    private PopupWindow popupWindow;

    public VaryViewHelper(View view) {
        super();
        this.view = view;
    }
    private void init() {
        params = view.getLayoutParams();
        if (view.getParent() != null) { //实例化时传入的View的父布局不为空时
            parentView = (ViewGroup) view.getParent();
        } else {//实例化时传入的View的父布局为空时，加载根视图布局
            parentView = (ViewGroup) view.getRootView().findViewById(android.R.id.content);
        }

        int count = parentView.getChildCount();
        for (int index = 0; index < count; index++) {
            if (view == parentView.getChildAt(index)) {
                viewIndex = index;
                break;
            }
        }

        currentView = view;
    }


    @Override
    public void restoreView() {
        showLayout(view);
    }

    @Override
    public void showLayout(View view) {


        if (parentView == null) {
            init();
        }
        //在当前显示的View赋值为传入的View
        this.currentView = view;
        //当父布局中指定位置上的View不是传入的View时，则需要显示为当前传入的View
        if (parentView.getChildAt(viewIndex) != view) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            //删除父布局指定位置上的VIEW
            parentView.removeViewAt(viewIndex);
            //将传入的View显示在父布局的指定位置上
            parentView.addView(view, viewIndex, params);
        }
    }

    @Override
    public View inflate(int layoutId) {
        if (view == null) {
            return null;
        }
        return LayoutInflater.from(MyApplication.getContext()).inflate(layoutId, null);
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public View getCurrentView() {
        return currentView;
    }

    @Override
    public void showLoading(View view) {

    }


    public void disLoading() {
        if (popupWindow != null) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }
//    public void setBackgroundAlpha(float bgAlpha) {
//        WindowManager.LayoutParams lp = ((Activity) MyApplication.getContext()).getWindow()
//                .getAttributes();
//        lp.alpha = bgAlpha;
//        ((Activity) MyApplication.getContext()).getWindow().setAttributes(lp);
//    }
}
