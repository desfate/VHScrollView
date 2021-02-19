package com.github.desfate.vhscrollview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.desfate.vhscrollview.R;
import com.github.desfate.vhscrollview.beans.ContentBean;
import com.github.desfate.vhscrollview.models.ContentModel;
import com.github.desfate.vhscrollview.tools.DeviceUtil;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {

    Context context;
    List<ContentBean> beans = new ArrayList<>();
    int width;
    int height;


    public ItemListAdapter(Context context, List<ContentBean> beans) {
        super();
        this.context = context;
        this.beans = beans;
    }

    public void setTextSize(int width, int height){
        this.width = width;
        this.height = height;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hv_item_list, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemName = view.findViewById(R.id.content_text);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(DeviceUtil.dpToPx(context, width)
                , DeviceUtil.dpToPx(context, height));
        holder.itemName.setLayoutParams(lp);
        holder.itemName.setTextSize(DeviceUtil.dpToPx(context, 4));
        holder.itemName.setPadding(40,0,0,0);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.itemName.setText(beans.get(position).getContent());
        if (beans.get(position).getColor() == 1) {
            holder.itemName.setTextColor(context.getResources().getColor(R.color.p_up_color));
        } else if (beans.get(position).getColor() == -1) {
            holder.itemName.setTextColor(context.getResources().getColor(R.color.p_down_color));
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemName;

        public ViewHolder (View view)
        {
            super(view);
        }

    }
}
