package com.android.wx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.model.VipInfoBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ClassName VipSearchAdapter
 * @Description TODO
 * @Author Administrator
 * @Date 2021/1/28 17:23
 */
public class GifeSearchAdapter extends RecyclerView.Adapter<GifeSearchAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<VipInfoBean> vipInfoBeans;
    private OnItemClick onItemClick;


    public GifeSearchAdapter(Context context, List<VipInfoBean> vipInfoBeans){
        this.context = context;
        this.vipInfoBeans = vipInfoBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_vip,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.itemView.setTag(position);


    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onClick(View view) {
        if (onItemClick != null){
            onItemClick.onItemClick((Integer) view.getTag());
        }
    }

    public void setListener(OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick{
        public void onItemClick(int position);
    }

    class ViewHolder extends  RecyclerView.ViewHolder{

        TextView tvVipCard;
        TextView tvVipUserName;
        TextView tvVipBalance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvVipCard = itemView.findViewById(R.id.tv_vip_card_id);
            tvVipUserName = itemView.findViewById(R.id.tv_vip_card_name);
            tvVipBalance = itemView.findViewById(R.id.tv_vip_card_balance);

        }
    }

}
