package com.android.wx.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.model.VipInfoBean;

import androidx.annotation.NonNull;

/**
 * @ClassName CustomerDialog
 * @Description TODO
 * @Author Administrator
 * @Date 2021/1/29 1:07
 */
public class CustomerDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private VipInfoBean vipInfoBean;

    public CustomerDialog(@NonNull Context context, VipInfoBean vipInfoBean) {
        super(context);
        this.context = context;
        this.vipInfoBean = vipInfoBean;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_customer);

        LinearLayout backLayout = findViewById(R.id.ll_remarks_back);
        backLayout.setOnClickListener(this);

        TextView tvCustomerUsername = findViewById(R.id.tv_customer_username);
        TextView tvCustomerSurname = findViewById(R.id.tv_customer_surname);
        TextView tvCustomerBirth = findViewById(R.id.tv_customer_birth);
        TextView tvCustomerTel = findViewById(R.id.tv_customer_tel);
        TextView tvCustomerEmail = findViewById(R.id.tv_customer_email);
        TextView tvCustomerAddress = findViewById(R.id.tv_customer_address);

        tvCustomerUsername.setText(vipInfoBean.getUserName());
        tvCustomerSurname.setText(vipInfoBean.getSurName());
        tvCustomerBirth.setText(vipInfoBean.getBirthday());
        tvCustomerTel.setText(vipInfoBean.getTelphone());
        tvCustomerEmail.setText(vipInfoBean.getEmail());
        tvCustomerAddress.setText(vipInfoBean.getAddress());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_remarks_back:

                dismiss();

                break;
        }
    }
}
