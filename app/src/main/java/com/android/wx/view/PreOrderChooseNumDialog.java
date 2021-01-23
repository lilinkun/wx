package com.android.wx.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.wx.R;
import com.android.wx.adapter.PreOrderChooseNumAdapter;
import com.android.wx.utils.UToast;
import com.android.wx.weight.SpaceItemDecoration;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PreOrderChooseNumDialog extends Dialog implements PreOrderChooseNumAdapter.OnPreOrderChooseNumListener, View.OnClickListener {

    private Context context;
    private RecyclerView rvDialogChooseNum;
    private EditText etChooseNum;
    private ArrayList<String> strings;
    private LinearLayout llRemarksBack;
    private OnOreOrderChooseNumListener onOreOrderChooseNumListener;

    public PreOrderChooseNumDialog(@NonNull Context context,OnOreOrderChooseNumListener onOreOrderChooseNumListener) {
        super(context);
        this.context = context;
        this.onOreOrderChooseNumListener = onOreOrderChooseNumListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_choose_num);

        llRemarksBack = findViewById(R.id.ll_remarks_back);
        llRemarksBack.setOnClickListener(this);
        rvDialogChooseNum = findViewById(R.id.rv_dialog_choose_num);
        etChooseNum = findViewById(R.id.et_chosse_num);

        strings = new ArrayList();

        for (int i = 1; i < 10;i++){
            strings.add(i+"");
        }

        strings.add("X");
        strings.add("0");
        strings.add("OK");

        PreOrderChooseNumAdapter preOrderChooseNumAdapter = new PreOrderChooseNumAdapter(context,strings);

        preOrderChooseNumAdapter.setListener(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3);

        rvDialogChooseNum.addItemDecoration(new SpaceItemDecoration(20, SpaceItemDecoration.GRIDLAYOUT));

        rvDialogChooseNum.setLayoutManager(gridLayoutManager);

        rvDialogChooseNum.setAdapter(preOrderChooseNumAdapter);

    }

    @Override
    public void onItemClick(int position) {

        if (strings.get(position).equals("X")){
            if (etChooseNum.getText().toString().trim().length() > 0  && Integer.valueOf(etChooseNum.getText().toString()) > 0) {
                etChooseNum.setText(etChooseNum.getText().toString().substring(0,etChooseNum.getText().length()-1));
            }else {
                UToast.show(context, R.string.input_food_num);
            }
        }else if (strings.get(position).equals("OK")){
            if (etChooseNum.getText().toString().trim().length() > 0  && Integer.valueOf(etChooseNum.getText().toString()) > 0) {
                onOreOrderChooseNumListener.onChooseNumClick(Integer.valueOf(etChooseNum.getText().toString()));
                dismiss();
            }else {
                UToast.show(context, R.string.input_food_num);
            }
        }else if(strings.get(position).equals("0")){
            if (etChooseNum.getText().toString().trim().length() > 0  && Integer.valueOf(etChooseNum.getText().toString()) > 0) {
                etChooseNum.append(strings.get(position));
            }else {
                UToast.show(context, R.string.input_food_num);
            }
        }else {
            etChooseNum.append(strings.get(position));
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.ll_remarks_back:

                dismiss();

                break;
        }
    }


    public interface OnOreOrderChooseNumListener{
        public void onChooseNumClick(int num);
    }

}
