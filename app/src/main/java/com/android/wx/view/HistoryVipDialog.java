package com.android.wx.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.android.wx.R;
import com.android.wx.adapter.HistoryVipAdapter;
import com.android.wx.db.DBManager;
import com.android.wx.model.VipInfoBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ClassName HistoryVipDialog
 * @Description TODO
 * @Author Administrator
 * @Date 2021/1/28 21:55
 */
public class HistoryVipDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private String vipCardId;

    public HistoryVipDialog(@NonNull Context context,String vipCardId) {
        super(context);
        this.context = context;
        this.vipCardId = vipCardId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_history_vip);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_dialog_history);

        LinearLayout backLayout = findViewById(R.id.ll_remarks_back);
        backLayout.setOnClickListener(this);

        List<VipInfoBean> vipInfoBeans = DBManager.getInstance(context).queryVipCard(vipCardId);

        HistoryVipAdapter historyVipAdapter = new HistoryVipAdapter(context,vipInfoBeans);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(historyVipAdapter);

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
