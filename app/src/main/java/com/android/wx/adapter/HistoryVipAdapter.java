package com.android.wx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.model.VipInfoBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ClassName HistoryVipAdapter
 * @Description TODO
 * @Author Administrator
 * @Date 2021/1/28 22:57
 */
public class HistoryVipAdapter extends RecyclerView.Adapter<HistoryVipAdapter.ViewHolder> {

    private Context context;
    private List<VipInfoBean> vipInfoBeans;

    public HistoryVipAdapter(Context context,List<VipInfoBean> vipInfoBeans) {
        this.context = context;
        this.vipInfoBeans = vipInfoBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_history_vip,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date date = new Date(Long.valueOf(vipInfoBeans.get(position).getStartTime()));

        holder.tvStartTime.setText(simpleDateFormat.format(date));
        holder.tvHistoryBalance.setText(vipInfoBeans.get(position).getBalance());
        holder.tvUseIntegral.setText(vipInfoBeans.get(position).getIntegral());
        holder.tvBalance.setText(vipInfoBeans.get(position).getBalance());
        holder.tvOperator.setText(vipInfoBeans.get(position).getOperator());
        holder.tvOrder.setText("9839283923");


    }

    @Override
    public int getItemCount() {
        return vipInfoBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvStartTime;
        TextView tvUseIntegral;
        TextView tvHistoryBalance;
        TextView tvBalance;
        TextView tvOperator;
        TextView tvOrder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStartTime = itemView.findViewById(R.id.tv_history_time);
            tvUseIntegral = itemView.findViewById(R.id.tv_history_integral);
            tvHistoryBalance = itemView.findViewById(R.id.tv_history_balance);
            tvBalance = itemView.findViewById(R.id.tv_adapter_balance);
            tvOperator = itemView.findViewById(R.id.tv_operator);
            tvOrder = itemView.findViewById(R.id.tv_history_order);
        }
    }

}
