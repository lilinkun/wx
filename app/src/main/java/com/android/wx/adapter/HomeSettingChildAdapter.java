package com.android.wx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.model.SettingChildBean;
import com.android.wx.model.SettingGroupBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ClassName HomeSettingChildAdapter
 * @Description TODO
 * @Author Administrator
 * @Date 2021/2/4 1:51
 */
public class HomeSettingChildAdapter extends RecyclerView.Adapter<HomeSettingChildAdapter.ViewHolder> {

    private Context context;
    private List<SettingChildBean> settingChildBeans;

    public HomeSettingChildAdapter(Context context,List<SettingChildBean> settingChildBeans){
        this.context = context;
        this.settingChildBeans = settingChildBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_home_setting_child,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvHomeChild.setText(settingChildBeans.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return settingChildBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivHomeChild;
        TextView tvHomeChild;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHomeChild = itemView.findViewById(R.id.tv_home_setting_child);
            ivHomeChild = itemView.findViewById(R.id.iv_home_setting_child);
        }
    }
}
