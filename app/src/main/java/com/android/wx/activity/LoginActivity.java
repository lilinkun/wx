package com.android.wx.activity;

import android.content.Intent;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.wx.R;
import com.android.wx.base.activity.MvpActivity;
import com.android.wx.event.EventCenter;
import com.android.wx.presenter.UserInfoPresenter;
import com.android.wx.view.UserInfoView;
import com.android.wx.weight.CustomNumKeyView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by kai
 * on 2021/1/12
 */
public class LoginActivity extends MvpActivity<UserInfoView, UserInfoPresenter> implements CustomNumKeyView.CallBack, UserInfoView {
    @BindView(R.id.custom_login)
    CustomNumKeyView mCustomKeyView;
    @BindView(R.id.ed_login_pwd)
    EditText edPwd;
    @BindView(R.id.btn_login_confirm)
    ImageView ivConfirm;
    @BindView(R.id.btn_login_delect)
    ImageView ivDelect;
    List<String> his = new ArrayList<>();

    @Override
    public View getLoadingTargeView() {
        return null;
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.login_activity;
    }

    @Override
    protected void onReceiverEvent(EventCenter eventCenter) {

    }

    @Override
    public void initData() {
        mCustomKeyView.setOnCallBack(this);
        presenter.install(this);
        edPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        edPwd.setInputType(InputType.TYPE_NULL);
    }

    @NonNull
    @Override
    public UserInfoPresenter createPresenter() {
        return new UserInfoPresenter();
    }

    @Override
    public void clickNum(String num) {
        if (edPwd.getText().length() < 8) {
            edPwd.append(num);
        }
    }

    @Override
    public void deleteNum() {

    }

    @OnClick({R.id.btn_login_delect, R.id.btn_login_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_confirm:
                presenter.query(this,edPwd.getText().toString());
                break;
            case R.id.btn_login_delect:
                int last = edPwd.getText().length();
                if (last > 0) {
                    //删除最后一位
                    edPwd.getText().delete(last - 1, last);
                }
                break;
        }
    }

    @Override
    public void onLoginSucceed(Long userId) {
        shoToast(getResources().getString(R.string.str_login_succeed));
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLoginFailed() {
        shoToast(getResources().getString(R.string.str_login_failed));
    }
}
