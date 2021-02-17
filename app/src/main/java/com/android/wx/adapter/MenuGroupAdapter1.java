package com.android.wx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.model.MenuGroupBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ClassName MenuGroupAdapter1
 * @Description TODO
 * @Author Administrator
 * @Date 2021/2/8 19:55
 */
public class MenuGroupAdapter1 extends RecyclerView.Adapter<MenuGroupAdapter1.ViewHolder> {

    private Context context;
    private List<MenuGroupBean> menuGroupBeans;
    private OnMenuOrderListener onMenuOrderListener;
    private String payer;
    private int clickInt;

    public MenuGroupAdapter1(Context context,List<MenuGroupBean> menuGroupBeans){
        this.context = context;
        this.menuGroupBeans = menuGroupBeans;
    }

    public void setClickItem(String payer,int position){
        this.payer = payer;
        this.clickInt = position;
        notifyDataSetChanged();
    }

    public void setData(List<MenuGroupBean> menuGroupBeans){
        this.menuGroupBeans = menuGroupBeans;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_menu_group_item,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.priceName.setText(menuGroupBeans.get(position).getPriceName());


        MenuOrderAdapter1 menuOrderAdapter = new MenuOrderAdapter1(context,menuGroupBeans.get(position).getMenuInfos(),false);
        menuOrderAdapter.setListener(new MenuOrderAdapter.OnMenuOrderListener() {
            @Override
            public void onItemClick(int position) {
                onMenuOrderListener.onItemClick(position);
            }

            @Override
            public void onRemarksListener(int position1, String remarks) {
                onMenuOrderListener.onRemarksListener(position,position1,remarks);
            }
        });


        if (payer.equals(menuGroupBeans.get(position).getPriceName())){
            menuOrderAdapter.setClickItem(menuGroupBeans.get(position).getMenuInfos().get(clickInt));
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        holder.rvFoodPrice.setLayoutManager(linearLayoutManager);

        holder.rvFoodPrice.setAdapter(menuOrderAdapter);

    }

    @Override
    public int getItemCount() {
        return menuGroupBeans.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }



    public void setListener(OnMenuOrderListener listener){
        onMenuOrderListener = listener;
    }

    public interface OnMenuOrderListener{
        public void onItemClick(int position);
        public void onRemarksListener(int groupId,int position,String remarks);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView priceName;
        private RecyclerView rvFoodPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rvFoodPrice = itemView.findViewById(R.id.rv_food_price);
            priceName = itemView.findViewById(R.id.tv_price_name);
        }
    }
}
