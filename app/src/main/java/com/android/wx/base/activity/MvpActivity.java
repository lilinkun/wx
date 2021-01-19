package com.android.wx.base.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.android.wx.mvp.callback.IActivityDelegateCallback;
import com.android.wx.mvp.callback.delegate.IActivityDelegate;
import com.android.wx.mvp.callback.delegate.impl.ActivityDelegate;
import com.android.wx.mvp.callback.presenter.IPresenter;
import com.android.wx.mvp.callback.view.IView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by kai
 * on 2021/1/13
 */
public abstract class MvpActivity<V extends IView, P extends IPresenter> extends BaseActivity implements IActivityDelegateCallback<V, P>, IView {
    public P presenter;
    protected IActivityDelegate<V, P> delegate;
    protected boolean isRetainInstance;
    protected String TAG = "MvpActivity";
    private int REQUEST_CODE_PERMISSION = 0x00099;
    private Unbinder unbinder;
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        unbinder = ButterKnife.bind(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getActivityDelegate().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getActivityDelegate().onDestroy();
        unbinder.unbind();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getActivityDelegate().onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getActivityDelegate().onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getActivityDelegate().onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getActivityDelegate().onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getActivityDelegate().onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getActivityDelegate().onRestart();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        getActivityDelegate().onContentChanged();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getActivityDelegate().onPostCreate(savedInstanceState);
    }

    /**
     * 获取当前Activity的生命周期代理回调接口
     *
     * @return
     */
    public IActivityDelegate<V, P> getActivityDelegate() {
        if (null == delegate) {
            delegate = new ActivityDelegate(this);
        }
        return delegate;
    }

    @NonNull
    public abstract P createPresenter();

    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    @Override
    public V getMvpView() {
        return (V) this;
    }


    @Override
    public AppCompatActivity getActivity() {
        return this;
    }

    @Override
    public boolean isRetainInstance() {
        return isRetainInstance;
    }

    @Override
    public void setRetainInstance(boolean retainingInstance) {
        this.isRetainInstance = retainingInstance;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean shouldInstanceBeRetained() {
        return isRetainInstance && isChangingConfigurations();
    }
    /**
     * 检测所有的权限是否都已授权
     * @param permissions
     * @return
     */
    private boolean checkPermissions(String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     */
    private List<String> getDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                needRequestPermissionList.add(permission);
            }
        }
        return needRequestPermissionList;
    }


    /**
     * 系统请求权限回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (verifyPermissions(grantResults)) {
                permissionSuccess(REQUEST_CODE_PERMISSION);
            } else {
                permissionFail(REQUEST_CODE_PERMISSION);
                showTipsDialog();
            }
        }
    }

    /**
     * 确认所有的权限是否都已授权
     *
     * @param grantResults
     * @return
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 显示提示对话框
     */
    private void showTipsDialog() {
        new AlertDialog.Builder(this)
                .setTitle("提示信息")
                .setMessage("当前应用缺少必要权限，该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                }).show();
    }

    /**
     * 启动当前应用设置页面
     */
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    /**
     * 获取权限成功
     *
     * @param requestCode
     */
    public void permissionSuccess(int requestCode) {
        Log.d(TAG, "获取权限成功=" + requestCode);

    }

    /**
     * 权限获取失败
     *
     * @param requestCode
     */
    public void permissionFail(int requestCode) {
        Log.d(TAG, "获取权限失败=" + requestCode);
    }

    /**
     * 请求权限
     *
     * @param permissions 请求的权限
     * @param requestCode 请求权限的请求码
     */
    public void requestPermission(String[] permissions, int requestCode) {
        this.REQUEST_CODE_PERMISSION = requestCode;
        if (checkPermissions(permissions)) {
            permissionSuccess(REQUEST_CODE_PERMISSION);
        } else {
            List<String> needPermissions = getDeniedPermissions(permissions);
            ActivityCompat.requestPermissions(this, needPermissions.toArray(new String[needPermissions.size()]), REQUEST_CODE_PERMISSION);
        }
    }

    public void shoToast(String str){
        Toast.makeText(this,str,Toast.LENGTH_LONG).show();
    }

    private void startActivity(Context context, Activity activity){
        Intent intent = new Intent(context,activity.getClass());
        startActivity(intent);
    }
}
