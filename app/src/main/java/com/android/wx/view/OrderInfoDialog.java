package com.android.wx.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.adapter.MenuOrderAdapter;
import com.android.wx.db.DBManager;
import com.android.wx.model.MenuInfo;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class OrderInfoDialog extends Dialog {

    private Unbinder unbinder;
    private Context context;
    private String orderId;
    private List<MenuInfo> menuInfos;

    @BindView(R.id.rv_dialog_order_menu)
    RecyclerView rvDialogOrderMenu;
    @BindView(R.id.tv_order_id)
    TextView tvOrderId;
    @BindView(R.id.tv_preorder_total_price)
    TextView tvPreorderTotalPrice;
    @BindView(R.id.tv_tax_price)
    TextView tvTaxPrice;
    @BindView(R.id.tv_preorder_total)
    TextView tvPreorderTotal;
    @BindView(R.id.tv_goods_num)
    TextView tvGoodsNum;

    public OrderInfoDialog(@NonNull Context context,String orderId) {
        super(context);
        this.context = context;
        this.orderId = orderId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_orderinfo);
        unbinder = ButterKnife.bind(this);

        tvOrderId.setText(orderId);

        menuInfos = DBManager.getInstance(context).queryOrderInfo(orderId);

        MenuOrderAdapter menuOrderAdapter = new MenuOrderAdapter(context,menuInfos,false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

        rvDialogOrderMenu.setLayoutManager(linearLayoutManager);

        rvDialogOrderMenu.setAdapter(menuOrderAdapter);

        settle();

    }

    //结算价格
    private void settle(){
        double totalPrice = 0;
        int stypeInt = 0;
        int totalInt = 0;

        if (menuInfos != null && menuInfos.size() > 0) {
            for (MenuInfo menuInfo : menuInfos) {
                stypeInt++;
                totalInt += menuInfo.getMenuFoodNum();
                totalPrice += menuInfo.getMenuPrice() * menuInfo.getMenuFoodNum();
            }
        }
        tvPreorderTotalPrice.setText(totalPrice + "");
        tvTaxPrice.setText(totalPrice * 0.1 + "");
        tvPreorderTotal.setText(stypeInt + "");
        tvGoodsNum.setText(totalInt + "");
    }

    @OnClick({R.id.ll_remarks_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ll_remarks_back:

                dismiss();

                break;

        }
    }




}
