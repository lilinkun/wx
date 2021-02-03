package com.android.wx.base.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    public Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(getlayoutId(), container, false);
        unbinder = ButterKnife.bind(this, v);
        //初始化事件和获取数据, 在此方法中获取数据不是懒加载模式
        initEventAndData();
        return v;
    }

    public abstract int getlayoutId();

    public abstract void initEventAndData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}