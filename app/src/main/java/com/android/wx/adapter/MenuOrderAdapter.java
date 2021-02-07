package com.android.wx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.model.MenuInfo;
import com.android.wx.view.PreOrderRemarksDialog;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MenuOrderAdapter extends RecyclerView.Adapter<MenuOrderAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<MenuInfo> menuInfos;
    private OnMenuOrderListener onMenuOrderListener;
    private MenuInfo clickMenuInfo;
    private boolean isShowIv;

    public MenuOrderAdapter(Context context , List<MenuInfo> menuInfos,boolean isShowIv){
        this.context = context;
        this.menuInfos = menuInfos;
        this.isShowIv = isShowIv;
    }

    public void setData(List<MenuInfo> menuInfos){
        this.menuInfos = menuInfos;
        notifyDataSetChanged();
    }

    public void setClickItem(MenuInfo clickItem){
        clickMenuInfo = clickItem;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_menu_order,parent,false);

        ViewHolder viewHolder = new ViewHolder(v);

        v.setOnClickListener(this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.itemView.setTag(position);

        holder.tvFoodName.setText(menuInfos.get(position).getMenuName());
        holder.tvFoodPrice.setText("$" + menuInfos.get(position).getMenuPrice());
        holder.tvMenuFoodNum.setText("x " + menuInfos.get(position).getMenuFoodNum());
        if (clickMenuInfo == menuInfos.get(position)){
            holder.itemView.setBackgroundResource(R.drawable.bg_menu_order_adapter_item_click);
        }else {
            holder.itemView.setBackgroundResource(R.drawable.bg_menu_order_adapter_item);
        }

        if(menuInfos.get(position).getMenuRemarks() == null){
            holder.rvPreorderRemarks.setVisibility(View.GONE);
        }else {
            holder.rvPreorderRemarks.setVisibility(View.VISIBLE);
        }

        List<String> strings = new ArrayList<>();

        if (!isShowIv){
            holder.ivMenuRemarks.setVisibility(View.GONE);
            initAdapter(holder.rvPreorderRemarks,menuInfos.get(position).getMenuRemarks());
        }else {
            holder.ivMenuRemarks.setVisibility(View.VISIBLE);
        }

        holder.ivMenuRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PreOrderRemarksDialog preOrderRemarksDialog = new PreOrderRemarksDialog(context, new PreOrderRemarksDialog.OnPreOrderRemarkListener() {
                    @Override
                    public void onRemarkStr(String remarks) {

                        holder.rvPreorderRemarks.setVisibility(View.VISIBLE);
                        onMenuOrderListener.onRemarksListener(position,remarks);

                        strings.add(remarks);

                        initAdapter(holder.rvPreorderRemarks,strings);
                    }
                });
                preOrderRemarksDialog.show();


            }
        });
    }

    public void initAdapter(RecyclerView recyclerView ,List<String> strings){

        RemarksAdapter remarksAdapter = new RemarksAdapter(context,strings);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(remarksAdapter);
    }

    @Override
    public int getItemCount() {
        return menuInfos.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onClick(View view) {
        if (onMenuOrderListener != null){
            onMenuOrderListener.onItemClick((Integer) view.getTag());
        }
    }

    public void setListener(OnMenuOrderListener listener){
        onMenuOrderListener = listener;
    }

    public interface OnMenuOrderListener{
        public void onItemClick(int position);
        public void onRemarksListener(int position,String remarks);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvFoodName;
        TextView tvFoodPrice;
        ImageView ivMenuRemarks;
        RecyclerView rvPreorderRemarks;
        TextView tvMenuFoodNum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFoodName = itemView.findViewById(R.id.tv_menu_food_name);
            tvFoodPrice = itemView.findViewById(R.id.tv_menu_food_price);
            ivMenuRemarks = itemView.findViewById(R.id.iv_menu_remarks);
            rvPreorderRemarks = itemView.findViewById(R.id.rv_preorder_remarks);
            tvMenuFoodNum = itemView.findViewById(R.id.tv_menu_food_num);
        }
    }
}
