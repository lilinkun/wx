package com.android.wx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.wx.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PreOrderChooseNumAdapter extends RecyclerView.Adapter<PreOrderChooseNumAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private ArrayList<String> strings;
    private OnPreOrderChooseNumListener onPreOrderChooseNumListener;

    public PreOrderChooseNumAdapter(Context context, ArrayList<String> strings){
        this.context = context;
        this.strings = strings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_choose_num,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.itemView.setTag(position);

        holder.tvChooseNum.setText(strings.get(position)+"");

        if (strings.get(position).equals("OK")){
            holder.tvChooseNum.setBackgroundResource(R.drawable.bg_adapter_choose_num_sure);
            holder.tvChooseNum.setTextColor(context.getResources().getColor(R.color.text_yellow_00));
        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public void setListener(OnPreOrderChooseNumListener onPreOrderChooseNumListener){
        this.onPreOrderChooseNumListener = onPreOrderChooseNumListener;
    }

    @Override
    public void onClick(View view) {
        if (onPreOrderChooseNumListener != null){
            onPreOrderChooseNumListener.onItemClick((Integer) (view.getTag()));
        }
    }

    public interface OnPreOrderChooseNumListener{
        public void onItemClick(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvChooseNum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChooseNum = itemView.findViewById(R.id.tv_choose_num);
        }
    }
}
