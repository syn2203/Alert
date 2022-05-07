package com.release.alert;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AlertNormalAdapter extends RecyclerView.Adapter<AlertNormalAdapter.ViewHolder> {

    private List<ItemBean> mAlertViewItems;
    private Context mContext;
    private AlertNormalAdapter.OnItemClickListener mOnItemClickListener;

    public AlertNormalAdapter(Context context, List<ItemBean> alertViewItems) {
        this.mContext = context;
        this.mAlertViewItems = alertViewItems;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(AlertNormalAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_alert_normal_view_bottom, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });
        }
        ItemBean bean = mAlertViewItems.get(position);
        if (bean.getColor() != 0)
            holder.tv_text.setTextColor(bean.getColor());

        holder.tv_text.setBackground(ContextCompat.getDrawable(mContext, R.drawable.alert_bottom_middle2_selector));
        holder.tv_text.setText(bean.name);
    }

    @Override
    public int getItemCount() {
        return mAlertViewItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_text;

        ViewHolder(View itemView) {
            super(itemView);
            tv_text = itemView.findViewById(R.id.tv_text);
        }
    }
}
