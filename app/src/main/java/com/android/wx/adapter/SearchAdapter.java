package com.android.wx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.wx.R;
import com.android.wx.db.DBManager;
import com.android.wx.model.MenuInfo;
import com.android.wx.model.OrderInfoBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ClassName SearchAdapter
 * @Description TODO
 * @Author liguo
 * @Date 2021/1/24 18:54
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context context;
    private ArrayList<OrderInfoBean> orderInfoBeans;

    public SearchAdapter(Context context, ArrayList<OrderInfoBean> orderInfoBeans){
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

        List<MenuInfo> menuInfos = DBManager.getInstance(context).queryOrderInfo("9839283923");

        MenuOrderAdapter menuOrderAdapter = new MenuOrderAdapter(context,menuInfos,false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

        holder.rvOrderMenu.setLayoutManager(linearLayoutManager);

        holder.rvOrderMenu.setAdapter(menuOrderAdapter);

    }

    @Override
    public int getItemCount() {
        return orderInfoBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        RecyclerView rvOrderMenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
