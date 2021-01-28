package com.android.wx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.db.DBManager;
import com.android.wx.model.Table;
import com.android.wx.utils.TimeUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeDataAdapter extends RecyclerView.Adapter<HomeDataAdapter.ViewHolder> implements View.OnClickListener {

    private List<Table> tables;
    private Context context;
    //type 0:全部展示 1:价格 2：时长 3：单号 4：时间 5：人数 6：状态 7：前台  8：顾客姓名
    private int type = 1;
    private OnHomeDataClickListener onHomeDataClickListener;
    private String floor;

    public HomeDataAdapter(Context context,String floor){
        this.context = context;
        this.floor = floor;
        this.tables = DBManager.getInstance(context).queryTable(floor);
    }

    public void setType(int type){
        this.type = type;
        notifyDataSetChanged();
    }

    public void setData(List<Table> tables){
        this.tables = tables;
        notifyDataSetChanged();
    }

    public void setFloor(String floor){
        this.tables = DBManager.getInstance(context).queryTable(floor);
        notifyDataSetChanged();
    }

    /*public void setTables(List<Table> allTables,String floor){
        this.tables = new ArrayList<>();
        for (int i = 0;i<allTables.size();i++){
            if (allTables.get(i).getFloorName().equals(floor)){
                this.tables.add(allTables.get(i));
            }
        }
    }*/

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_table,parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        v.setOnClickListener(this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.itemView.setTag(position);

        if (tables.get(position).getStatue().equals(context.getString(R.string.idle))){
            holder.rlDiningTop.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_dining_hall_red));
            holder.mTvUnprice.setVisibility(View.VISIBLE);
            holder.rlPrice.setVisibility(View.INVISIBLE);
            holder.mTableStatus.setText(tables.get(position).getStatue()+"");
            holder.mTablePerNo.setVisibility(View.INVISIBLE);
            holder.mTableStatus.setTextColor(context.getResources().getColor(R.color.gray_line_99));
        }else {
            holder.rlDiningTop.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_dining_hall));
            holder.mTvUnprice.setVisibility(View.GONE);
            holder.rlPrice.setVisibility(View.VISIBLE);


            holder.mPrice1.setText(tables.get(position).getAmount1()+"");
            holder.mPrice2.setText(tables.get(position).getAmount2()+"");
            holder.mPrice3.setText(tables.get(position).getAmount3()+"");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            if (type == 1){
                holder.mTableStatus.setText(tables.get(position).getTotalAmountPrice()+"");
            }else if (type == 2){
                long timeNow = (new Date()).getTime() - tables.get(position).getTime();
                holder.mTableStatus.setText(TimeUtil.timeToDate(timeNow) +"");
            }else if (type == 3){
                holder.mTableStatus.setText(tables.get(position).getOrderNumber()+"");
            }else if (type == 4){
                long timeOld = tables.get(position).getTime();
                holder.mTableStatus.setText(simpleDateFormat.format(timeOld)+"");
                holder.mTableStatus.setTextColor(context.getResources().getColor(R.color.title_color));
            }else if (type == 5){
                holder.mTableStatus.setText(tables.get(position).getPersionNo()+"");
            }else if (type == 6){
                holder.mTableStatus.setText(tables.get(position).getStatue()+"");
            }else if (type == 7){
                holder.mTableStatus.setText(tables.get(position).getStatue()+"");
            }else if (type == 8){
                holder.mTableStatus.setText(tables.get(position).getCustomerName()+"");
            }else if (type == 9){
                holder.mTablePerNo.setVisibility(View.INVISIBLE);
                holder.mPrice1.setText("****");
                holder.mPrice2.setText("****");
                holder.mPrice3.setText("****");
            }else if (type == 10){
                holder.mTablePerNo.setVisibility(View.VISIBLE);
                holder.mPrice1.setText(tables.get(position).getAmount1()+"");
                holder.mPrice2.setText(tables.get(position).getAmount2()+"");
                holder.mPrice3.setText(tables.get(position).getAmount3()+"");
            }

        }

        holder.mTableName.setText(tables.get(position).getName());
        holder.mTablenNumber.setText("("+tables.get(position).getTableNum() + "号桌)");
        holder.mTablePerNo.setText(tables.get(position).getPersionNo()+"人");




    }

    @Override
    public int getItemCount() {
        return tables.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onClick(View view) {
        if (onHomeDataClickListener != null){
            onHomeDataClickListener.onItemClick(tables.get((Integer) view.getTag()));
        }
    }

    public void setListener(OnHomeDataClickListener onHomeDataClickListener){
        this.onHomeDataClickListener = onHomeDataClickListener;
    }

    public interface OnHomeDataClickListener{
        public void onItemClick(Table table);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView mTableName;
        TextView mTablenNumber;
        TextView mPrice1;
        TextView mPrice2;
        TextView mPrice3;
        TextView mTableStatus;
        TextView mTablePerNo;
        TextView mTvUnprice;
        RelativeLayout rlDiningTop;
        RelativeLayout rlPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTableName = (TextView) itemView.findViewById(R.id.item_table_name);
            mTablenNumber = (TextView) itemView.findViewById(R.id.item_table_number);
            mPrice1 = (TextView) itemView.findViewById(R.id.item_table_amount1);
            mPrice2 = (TextView) itemView.findViewById(R.id.item_table_amount2);
            mPrice3 = (TextView) itemView.findViewById(R.id.item_table_amount3);
            mTableStatus = (TextView) itemView.findViewById(R.id.item_table_status);
            mTablePerNo = (TextView) itemView.findViewById(R.id.item_table_persion);
            rlDiningTop = (RelativeLayout) itemView.findViewById(R.id.rl_dining_top);
            rlPrice = (RelativeLayout) itemView.findViewById(R.id.rl_price);
            mTvUnprice = (TextView) itemView.findViewById(R.id.tv_unprice);
        }
    }

}
