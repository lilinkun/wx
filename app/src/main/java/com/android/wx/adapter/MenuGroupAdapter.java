package com.android.wx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.model.MenuGroupBean;
import com.android.wx.model.MenuInfo;
import com.android.wx.view.PreOrderRemarksDialog;
import com.hgdendi.expandablerecycleradapter.BaseExpandableRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ClassName MenuGroupAdapter
 * @Description TODO
 * @Author Administrator
 * @Date 2021/2/7 22:13
 */
public class MenuGroupAdapter extends BaseExpandableRecyclerViewAdapter<MenuGroupBean, MenuInfo, MenuGroupAdapter.MenuGroupVH, MenuGroupAdapter.MenuChildVH> {

    Context context;
    List<MenuGroupBean> menuGroupBeans;
    private OnMenuOrderListener onMenuOrderListener;
    private MenuInfo clickMenuInfo;

    public MenuGroupAdapter(Context context, List<MenuGroupBean> menuGroupBeans){
        this.context = context;
        this.menuGroupBeans = menuGroupBeans;
    }

    @Override
    public int getGroupCount() {
        return menuGroupBeans.size();
    }

    public void setClickItem(MenuInfo clickItem){
        clickMenuInfo = clickItem;
        notifyDataSetChanged();
    }

    public void setData(List<MenuGroupBean> menuGroupBeans){
        this.menuGroupBeans = menuGroupBeans;
        notifyDataSetChanged();
    }

    @Override
    public MenuGroupBean getGroupItem(int groupIndex) {

        return menuGroupBeans.get(groupIndex);
    }

    @Override
    public MenuGroupVH onCreateGroupViewHolder(ViewGroup parent, int groupViewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_menu_group_item,parent,false);

        MenuGroupVH menuGroupVH = new MenuGroupVH(view);

        return menuGroupVH;
    }

    @Override
    public void onBindGroupViewHolder(MenuGroupVH holder, MenuGroupBean groupBean, boolean isExpand) {

        if (menuGroupBeans.size() > 1){
            holder.itemView.setVisibility(View.VISIBLE);
        }else {
            holder.itemView.setVisibility(View.GONE);
        }
        isExpand = true;

        holder.priceName.setText(groupBean.getPriceName());
    }

    @Override
    public MenuChildVH onCreateChildViewHolder(ViewGroup parent, int childViewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.adapter_menu_order,parent,false);

        MenuChildVH childVH = new MenuChildVH(v);

        return childVH;
    }

    @Override
    public void onBindChildViewHolder(MenuChildVH holder, MenuGroupBean groupBean, MenuInfo menuInfo) {

        holder.tvFoodName.setText(menuInfo.getMenuName());
        holder.tvFoodPrice.setText("$" + menuInfo.getMenuPrice());
        holder.tvMenuFoodNum.setText("x " + menuInfo.getMenuFoodNum());
        if (clickMenuInfo == menuInfo){
            holder.itemView.setBackgroundResource(R.drawable.bg_menu_order_adapter_item_click);
        }else {
            holder.itemView.setBackgroundResource(R.drawable.bg_menu_order_adapter_item);
        }

        if(menuInfo.getMenuRemarks() == null){
            holder.rvPreorderRemarks.setVisibility(View.GONE);
        }else {
            holder.rvPreorderRemarks.setVisibility(View.VISIBLE);
        }

        List<String> strings = new ArrayList<>();

        holder.ivMenuRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PreOrderRemarksDialog preOrderRemarksDialog = new PreOrderRemarksDialog(context, new PreOrderRemarksDialog.OnPreOrderRemarkListener() {
                    @Override
                    public void onRemarkStr(String remarks) {

                        holder.rvPreorderRemarks.setVisibility(View.VISIBLE);
                        onMenuOrderListener.onRemarksListener(groupBean,menuInfo,remarks);

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

    class MenuGroupVH extends BaseExpandableRecyclerViewAdapter.BaseGroupViewHolder{

        private TextView priceName;

        public MenuGroupVH(View itemView) {
            super(itemView);
            priceName = itemView.findViewById(R.id.tv_price_name);
        }

        @Override
        protected void onExpandStatusChanged(RecyclerView.Adapter adapter, boolean b) {

           adapter.notifyItemChanged(getAdapterPosition());

        }
    }

    public void setGroupListener(OnMenuOrderListener listener){
        onMenuOrderListener = listener;
    }

    public interface OnMenuOrderListener{
        public void onRemarksListener(MenuGroupBean menuGroupBean,MenuInfo menuInfo,String remarks);
    }

    class MenuChildVH extends RecyclerView.ViewHolder{
        TextView tvFoodName;
        TextView tvFoodPrice;
        ImageView ivMenuRemarks;
        RecyclerView rvPreorderRemarks;
        TextView tvMenuFoodNum;

        public MenuChildVH(@NonNull View itemView) {
            super(itemView);
            tvFoodName = itemView.findViewById(R.id.tv_menu_food_name);
            tvFoodPrice = itemView.findViewById(R.id.tv_menu_food_price);
            ivMenuRemarks = itemView.findViewById(R.id.iv_menu_remarks);
            rvPreorderRemarks = itemView.findViewById(R.id.rv_preorder_remarks);
            tvMenuFoodNum = itemView.findViewById(R.id.tv_menu_food_num);
        }
    }


}
