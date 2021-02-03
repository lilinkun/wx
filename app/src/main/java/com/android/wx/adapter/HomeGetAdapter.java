package com.android.wx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.model.MenuInfo;
import com.android.wx.model.Table;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ClassName HomeGetAdapter
 * @Description TODO
 * @Author liguo
 * @Date 2021/1/23 17:04
 */
public class HomeGetAdapter extends RecyclerView.Adapter<HomeGetAdapter.ViewHolder> {

    private Context context;
    private List<Table> menuInfos;

    public HomeGetAdapter(Context context, List<Table> menuInfos){
        this.context = context;
        this.menuInfos = menuInfos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_home_get,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvGetOrderId.setText("9839283923");
        holder.tvGetTableId.setText("2a桌 3人");
        holder.tvGetTime.setText("22:22:22");
        holder.tvGetPrice.setText("$222");
        holder.tvGetGoOrder.setText("劳先生");


    }

    @Override
    public int getItemCount() {
        return 8;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvGetOrderId;
        TextView tvGetGoOrder;
        TextView tvGetTableId;
        TextView tvGetTime;
        TextView tvGetPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvGetOrderId = itemView.findViewById(R.id.tv_order_id);
            tvGetGoOrder = itemView.findViewById(R.id.tv_get_go_order);
            tvGetTableId = itemView.findViewById(R.id.tv_get_table_number);
            tvGetTime = itemView.findViewById(R.id.tv_get_time);
            tvGetPrice = itemView.findViewById(R.id.tv_get_price);
        }
    }

}
