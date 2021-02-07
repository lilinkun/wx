package com.android.wx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.model.MenuInfo;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PreOrderMenuAdapter extends RecyclerView.Adapter<PreOrderMenuAdapter.ViewHolder> implements View.OnClickListener {

    Context context;
    ArrayList<MenuInfo> menuInfos;
    OnItemPreOrderClick onItemPreOrderClick;

    public PreOrderMenuAdapter(Context context , ArrayList<MenuInfo> menuInfos){
        this.context = context;
        this.menuInfos = menuInfos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_pre_order_menu, null);

        ViewHolder viewHolder = new ViewHolder(v);

        v.setOnClickListener(this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.itemView.setTag(position);

        holder.tvFoodName.setText(menuInfos.get(position).getMenuName());
        holder.tvFoodPrice.setText("$" + menuInfos.get(position).getMenuPrice());


        if (menuInfos.get(position).isChoose()){
            holder.ivChoose.setVisibility(View.VISIBLE);
        }else {
            holder.ivChoose.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return menuInfos.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void setPreOrderListener(OnItemPreOrderClick onItemPreOrderClick){
        this.onItemPreOrderClick = onItemPreOrderClick;
    }

    @Override
    public void onClick(View view) {
        if (onItemPreOrderClick != null) {
            onItemPreOrderClick.onItemOrderClick((Integer) view.getTag());
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvFoodName;
        TextView tvFoodPrice;
        ImageView ivChoose;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFoodName = itemView.findViewById(R.id.tv_food_name);
            tvFoodPrice = itemView.findViewById(R.id.tv_food_price);
            ivChoose = itemView.findViewById(R.id.ic_choose);
        }
    }

    public interface OnItemPreOrderClick{
        void onItemOrderClick(int positon);
    }
}
