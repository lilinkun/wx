package com.android.wx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.wx.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RemarksAdapter extends RecyclerView.Adapter<RemarksAdapter.ViewHolder> {

    private Context context;
    private List<String> strings;

    public RemarksAdapter(Context context, List<String> strings){
        this.context = context;
        this.strings = strings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_remarks,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvRemarks.setText(strings.get(position));

    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvRemarks;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRemarks = itemView.findViewById(R.id.tv_remarks);
        }
    }
}
