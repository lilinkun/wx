package com.android.wx.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.android.wx.R;
import com.android.wx.model.Table;

import androidx.annotation.NonNull;

/**
 * @ClassName ChangeTableDialog
 * @Description TODO
 * @Author Administrator
 * @Date 2021/2/6 20:49
 */
public class ChangeTableDialog extends Dialog {

    public ChangeTableDialog(@NonNull Context context, Table table) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_change_table_tip);
    }
}
