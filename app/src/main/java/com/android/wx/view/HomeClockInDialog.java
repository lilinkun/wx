package com.android.wx.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.android.wx.R;

import androidx.annotation.NonNull;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @ClassName HomeClockInDialog
 * @Description TODO
 * @Author liguo
 * @Date 2021/1/24 1:09
 */
public class HomeClockInDialog extends Dialog {

    private Unbinder unbinder;

    public HomeClockInDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_clock_in);
        unbinder = ButterKnife.bind(this);
    }


    @OnClick({R.id.ll_remarks_back,R.id.tv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:

            case R.id.ll_remarks_back:

                dismiss();

                break;



        }
    }

}
