package com.android.wx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.model.Table;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeDataAdapter extends RecyclerView.Adapter<HomeDataAdapter.ViewHolder> {

    private ArrayList<Table> tableDaos;
    private Context context;
    //type 0:全部展示 1:价格 2：时长 3：单号 4：时间 5：人数 6：状态 7：前台  8：顾客姓名
    private int type = 1;

    public HomeDataAdapter(ArrayList<Table> tableDaos, Context context){
        this.tableDaos = tableDaos;
        this.context = context;
    }

    public void setType(int type){
        this.type = type;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_table, null);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.mPrice1.setText(tableDaos.get(position).getAmount1()+"");
        holder.mPrice2.setText(tableDaos.get(position).getAmount2()+"");
        holder.mPrice3.setText(tableDaos.get(position).getAmount3()+"");
        holder.mTableName.setText(tableDaos.get(position).getName());
        holder.mTablenNumber.setText(tableDaos.get(position).getOrderNumber());
        holder.mTablePerNo.setText(tableDaos.get(position).getPersionNo()+"");

        if (type == 1){
            holder.mTableStatus.setText(tableDaos.get(position).getTotalAmountPrice()+"");
        }else if (type == 2){
            holder.mTableStatus.setText(tableDaos.get(position).getTimeOld()+"");
        }else if (type == 3){
            holder.mTableStatus.setText(tableDaos.get(position).getOrderNumber()+"");
        }else if (type == 4){
            holder.mTableStatus.setText(tableDaos.get(position).getTimeOld()+"");
        }else if (type == 5){
            holder.mTableStatus.setText(tableDaos.get(position).getPersionNo()+"");
        }else if (type == 6){
            holder.mTableStatus.setText(tableDaos.get(position).getStatue()+"");
        }else if (type == 7){
            holder.mTableStatus.setText(tableDaos.get(position).getStatue()+"");
        }else if (type == 8){
            holder.mTableStatus.setText(tableDaos.get(position).getCustomerName()+"");
        }else if (type == 9){
            holder.mTablePerNo.setVisibility(View.INVISIBLE);
            holder.mPrice1.setText("****");
            holder.mPrice2.setText("****");
            holder.mPrice3.setText("****");
        }else if (type == 10){
            holder.mTablePerNo.setVisibility(View.VISIBLE);
            holder.mPrice1.setText(tableDaos.get(position).getAmount1()+"");
            holder.mPrice2.setText(tableDaos.get(position).getAmount2()+"");
            holder.mPrice3.setText(tableDaos.get(position).getAmount3()+"");
        }

    }

    @Override
    public int getItemCount() {
        return tableDaos.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView mTableName;
        TextView mTablenNumber;
        TextView mPrice1;
        TextView mPrice2;
        TextView mPrice3;
        TextView mTableStatus;
        TextView mTablePerNo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTableName = (TextView) itemView.findViewById(R.id.item_table_name);
            mTablenNumber = (TextView) itemView.findViewById(R.id.item_table_number);
            mPrice1 = (TextView) itemView.findViewById(R.id.item_table_amount1);
            mPrice2 = (TextView) itemView.findViewById(R.id.item_table_amount2);
            mPrice3 = (TextView) itemView.findViewById(R.id.item_table_amount3);
            mTableStatus = (TextView) itemView.findViewById(R.id.item_table_status);
            mTablePerNo = (TextView) itemView.findViewById(R.id.item_table_persion);
        }
    }

}
