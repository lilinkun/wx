package com.android.wx.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.adapter.PreOrderChooseNumAdapter;
import com.android.wx.weight.SpaceItemDecoration;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PreOrderNumberDialog extends Dialog implements PreOrderChooseNumAdapter.OnPreOrderChooseNumListener, View.OnClickListener {

    private Context context;
    private EditText etSelfNum;
    private RecyclerView rvChooseCustomerNum;
    private ArrayList<String> strings;
    private TextView tvStartPreOrder;
    private OnPreOrderListener onPreOrderListener;
    private LinearLayout llRemarksBack;

    public PreOrderNumberDialog(@NonNull Context context,OnPreOrderListener onPreOrderListener) {
        super(context);
        this.context = context;
        this.onPreOrderListener = onPreOrderListener;
    }

    public PreOrderNumberDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected PreOrderNumberDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_preorder_chosse_num);

        initData();
    }

    private void initData() {

        llRemarksBack = findViewById(R.id.ll_remarks_back);
        llRemarksBack.setOnClickListener(this);

        rvChooseCustomerNum = findViewById(R.id.rv_choose_customer_num);

        etSelfNum = findViewById(R.id.et_self_num);

        tvStartPreOrder = findViewById(R.id.tv_start_preorder);

        tvStartPreOrder.setOnClickListener(this);

        strings = new ArrayList<>();

        for (int i = 1;i < 13;i++){
            strings.add(i+"");
        }

        PreOrderChooseNumAdapter preOrderChooseNumAdapter = new PreOrderChooseNumAdapter(context,strings);

        preOrderChooseNumAdapter.setListener(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,4);

        rvChooseCustomerNum.addItemDecoration(new SpaceItemDecoration(20, SpaceItemDecoration.GRIDLAYOUT));

        rvChooseCustomerNum.setLayoutManager(gridLayoutManager);

        rvChooseCustomerNum.setAdapter(preOrderChooseNumAdapter);


    }


    @Override
    public void onItemClick(int position) {
        etSelfNum.setText(strings.get(position)+"");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_start_preorder:

                onPreOrderListener.onCustomerNum(Integer.valueOf(etSelfNum.getText().toString()));
                dismiss();
                break;

            case R.id.ll_remarks_back:

                dismiss();

                break;
        }
    }


    public interface OnPreOrderListener{
        public void onCustomerNum(int num);
    }
}
