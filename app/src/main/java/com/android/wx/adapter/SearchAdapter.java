package com.android.wx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.db.DBManager;
import com.android.wx.model.MenuInfo;
import com.android.wx.model.OrderInfoBean;
import com.android.wx.utils.TimeUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ClassName SearchAdapter
 * @Description TODO
 * @Date 2021/1/24 18:54
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context context;
    private List<OrderInfoBean> orderInfoBeans;

    public SearchAdapter(Context context, List<OrderInfoBean> orderInfoBeans){
        this.context = context;
        this.orderInfoBeans = orderInfoBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_search,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        List<MenuInfo> menuInfos = DBManager.getInstance(context).queryOrderInfo(orderInfoBeans.get(position).getOrderId());

        holder.tvOrderId.setText(orderInfoBeans.get(position).getOrderId());

        holder.tvTotalPrice.setText(orderInfoBeans.get(position).getTotalPrice() + "");

        holder.tvGoodsNum.setText(orderInfoBeans.get(position).getTotal()+"");

        holder.tvTotalGoods.setText(orderInfoBeans.get(position).getType()+"");

        holder.tvTaxPrice.setText(orderInfoBeans.get(position).getTotalPrice()/10 + "");

        MenuOrderAdapter menuOrderAdapter = new MenuOrderAdapter(context,menuInfos,false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        holder.rvOrderMenu.setLayoutManager(linearLayoutManager);

        holder.rvOrderMenu.setAdapter(menuOrderAdapter);

        String mStr = TimeUtil.timeOld(Long.valueOf(orderInfoBeans.get(position).getOrderTime()));

        holder.tvOldTime.setText(mStr);

    }

    @Override
    public int getItemCount() {
        return orderInfoBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        RecyclerView rvOrderMenu;
        TextView tvOrderId;
        TextView tvTotalPrice;
        TextView tvTotalGoods;
        TextView tvGoodsNum;
        TextView tvTaxPrice;
        TextView tvOldTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rvOrderMenu = itemView.findViewById(R.id.rv_dialog_order_menu);
            tvOrderId = itemView.findViewById(R.id.tv_order_id);
            tvTotalPrice = itemView.findViewById(R.id.tv_preorder_total_price);
            tvTotalGoods = itemView.findViewById(R.id.tv_preorder_total);
            tvGoodsNum = itemView.findViewById(R.id.tv_goods_num);
            tvTaxPrice = itemView.findViewById(R.id.tv_tax_price);
            tvOldTime = itemView.findViewById(R.id.tv_order_time);
        }
    }

}
