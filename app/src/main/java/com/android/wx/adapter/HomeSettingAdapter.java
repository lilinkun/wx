package com.android.wx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.model.SettingGroupBean;
import com.android.wx.weight.SpaceItemDecoration;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ClassName HomeSettingAdapter
 * @Description TODO
 * @Author Administrator
 * @Date 2021/2/4 1:09
 */
public class HomeSettingAdapter extends RecyclerView.Adapter<HomeSettingAdapter.ViewHolder> {

    private Context context;
    private List<SettingGroupBean> settingGroupBeans;

    public HomeSettingAdapter(Context context,List<SettingGroupBean> settingGroupBeans){
        this.context = context;
        this.settingGroupBeans = settingGroupBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_home_setting,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvHomeSetting.setText(settingGroupBeans.get(position).getTitle());

        HomeSettingChildAdapter homeSettingChildAdapter = new HomeSettingChildAdapter(context,settingGroupBeans.get(position).getSubData());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,5);

        holder.rvHomeSetting.addItemDecoration(new SpaceItemDecoration(20, SpaceItemDecoration.GRIDLAYOUT));

        holder.rvHomeSetting.setLayoutManager(gridLayoutManager);

        holder.rvHomeSetting.setAdapter(homeSettingChildAdapter);


    }

    @Override
    public int getItemCount() {
        return settingGroupBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        RecyclerView rvHomeSetting;
        TextView tvHomeSetting;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHomeSetting = itemView.findViewById(R.id.tv_home_setting);
            rvHomeSetting = itemView.findViewById(R.id.rv_home_setting);
        }
    }

}
