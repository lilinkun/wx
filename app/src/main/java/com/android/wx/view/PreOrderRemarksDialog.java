package com.android.wx.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.wx.R;

import androidx.annotation.NonNull;

public class PreOrderRemarksDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private OnPreOrderRemarkListener onPreOrderRemarkListener;
    private TextView tvSureRemarks;
    private EditText etRemarksInfo;
    private EditText etRemarksMoney;
    private LinearLayout llRemarksBack;

    public PreOrderRemarksDialog(@NonNull Context context,OnPreOrderRemarkListener onPreOrderRemarkListener) {
        super(context);
        this.context = context;
        this.onPreOrderRemarkListener = onPreOrderRemarkListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_remarks);

        initData();

    }

    private void initData(){

        tvSureRemarks = findViewById(R.id.tv_sure_remarks);
        tvSureRemarks.setOnClickListener(this);

        etRemarksInfo= findViewById(R.id.et_remarks_info);
        etRemarksMoney = findViewById(R.id.et_remarks_money);

        llRemarksBack = findViewById(R.id.ll_remarks_back);
        llRemarksBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_sure_remarks:

                if (onPreOrderRemarkListener != null){
                        String remarks = "";
                        if (etRemarksMoney.getText().toString().trim().length() > 0){
                            remarks = etRemarksInfo.getText().toString() + "  ￥" +  etRemarksMoney.getText().toString();
//                            onPreOrderRemarkListener.onRemarkStr(etRemarksInfo.getText().toString() + "  ￥" +  etRemarksMoney.getText().toString());
                        }else {
                            remarks = etRemarksInfo.getText().toString();
//                            onPreOrderRemarkListener.onRemarkStr(etRemarksInfo.getText().toString());
                        }

                        if (!remarks.equals("")){
                            onPreOrderRemarkListener.onRemarkStr(remarks);
                            dismiss();
                        }

                }

                break;

            case R.id.ll_remarks_back:

                dismiss();

                break;
        }
    }


    public interface OnPreOrderRemarkListener{
        public void onRemarkStr(String remarks);
    }
}
