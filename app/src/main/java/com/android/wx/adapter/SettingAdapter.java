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
import com.hgdendi.expandablerecycleradapter.BaseExpandableRecyclerViewAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ClassName SettingAdapter
 * @Description TODO
 * @Author Administrator
 * @Date 2021/2/3 13:20
 */
public class SettingAdapter extends BaseExpandableRecyclerViewAdapter<SettingGroupBean, SettingChildBean,SettingAdapter.SettingGroupVH, SettingAdapter.SettingChildVH> {
    private List<SettingGroupBean> data;
    private Context context;

    public SettingAdapter(Context context,List<SettingGroupBean> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public SettingGroupBean getGroupItem(int groupIndex) {
        return data.get(groupIndex);
    }

    @Override
    public SettingGroupVH onCreateGroupViewHolder(ViewGroup parent, int groupViewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_setting_group,parent,false);

        SettingGroupVH settingGroupVH = new SettingGroupVH(view);

        return settingGroupVH;
    }

    @Override
    public void onBindGroupViewHolder(SettingGroupVH holder, SettingGroupBean groupBean, boolean isExpand) {

        holder.groupTitle.setText(groupBean.getTitle());

    }

    @Override
    public SettingChildVH onCreateChildViewHolder(ViewGroup parent, int childViewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_setting_child,parent,false);

        SettingChildVH settingChildVH = new SettingChildVH(view);

        return settingChildVH;
    }

    @Override
    public void onBindChildViewHolder(SettingChildVH settingChildVH, SettingGroupBean groupBean, SettingChildBean settingChildBean) {
        settingChildVH.childTitle.setText(settingChildBean.getName());
    }

    class SettingGroupVH extends BaseExpandableRecyclerViewAdapter.BaseGroupViewHolder{
        public TextView groupTitle;
        public ImageView state;

        public SettingGroupVH(View itemView) {
            super(itemView);
            groupTitle=itemView.findViewById(R.id.group);
            state=itemView.findViewById(R.id.right_state);
        }

        @Override
        protected void onExpandStatusChanged(RecyclerView.Adapter adapter, boolean isExpanding) {
            if(isExpanding){
                state.setImageResource(R.drawable.ic_arrow_top);
            }else{
                state.setImageResource(R.drawable.ic_arrow_bottom);
            }
            adapter.notifyItemChanged(getAdapterPosition());
        }
    }

    class SettingChildVH extends RecyclerView.ViewHolder{

        public TextView childTitle;
        public SettingChildVH(@NonNull View itemView) {
            super(itemView);
            childTitle=itemView.findViewById(R.id.child);
        }
    }

}
