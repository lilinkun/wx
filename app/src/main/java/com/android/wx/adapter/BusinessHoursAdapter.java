package com.android.wx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.db.DBManager;
import com.android.wx.model.BusinessHoursBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ClassName BusinessHoursAdapter
 * @Description TODO
 * @Author Administrator
 * @Date 2021/2/5 22:26
 */
public class BusinessHoursAdapter extends RecyclerView.Adapter<BusinessHoursAdapter.ViewHolder> {

    private Context context;
    private List<BusinessHoursBean> businessHoursBeans;

    public BusinessHoursAdapter(Context context, List<BusinessHoursBean> businessHoursBeans){
        this.context = context;
        this.businessHoursBeans = businessHoursBeans;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_business_hours,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    public void setData(List<BusinessHoursBean> businessHoursBeans){
        this.businessHoursBeans = businessHoursBeans;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvBusinessName.setText(businessHoursBeans.get(position).getBusinessName());
        holder.tvBusinessTime.setText(businessHoursBeans.get(position).getBusinessTime());
        holder.tvBusinessWeek.setText(businessHoursBeans.get(position).getBusinessWeek());

        holder.tvBusinessDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBManager.getInstance(context).deleteBusinessHours(businessHoursBeans.get(position));
                businessHoursBeans = DBManager.getInstance(context).queryBusinessHours();
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return businessHoursBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvBusinessName;
        TextView tvBusinessTime;
        TextView tvBusinessWeek;
        TextView tvBusinessDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBusinessName = itemView.findViewById(R.id.tv_business_name);
            tvBusinessTime = itemView.findViewById(R.id.tv_business_time);
            tvBusinessWeek = itemView.findViewById(R.id.tv_business_week);
            tvBusinessDelete = itemView.findViewById(R.id.tv_business_delete);
        }
    }

}
