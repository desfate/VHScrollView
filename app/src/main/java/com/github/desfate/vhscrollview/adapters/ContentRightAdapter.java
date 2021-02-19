package com.github.desfate.vhscrollview.adapters;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.desfate.vhscrollview.R;
import com.github.desfate.vhscrollview.beans.ContentBean;
import com.github.desfate.vhscrollview.models.ContentModel;
import com.github.desfate.vhscrollview.tools.DeviceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 正文右侧适配器
 */
public class ContentRightAdapter extends BaseAdapter {
    private Context context;
    List<ContentModel> list;
    private int width;
    private int hight;

    public ContentRightAdapter(Context context, List<ContentModel> models) {
        super();
        this.context = context;
        this.list = models;
    }

    public void setData(List<ContentModel> models) {
        this.list = models;
        notifyDataSetChanged();
    }

    public void setSize(int width, int hight) {
        this.width = width;
        this.hight = hight;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold viewHold;
        if (convertView == null) {
            viewHold = new ViewHold();
            convertView = LayoutInflater.from(context).inflate(R.layout.hv_head_right_item, null);
            viewHold.item_list = convertView.findViewById(R.id.mRecyclerView);
            LinearLayoutManager manager = new LinearLayoutManager(context){
                @Override
                public boolean canScrollHorizontally() {
                    return false;
                }
            };

            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            viewHold.item_list.setLayoutManager(manager);
            if (list != null && list.size() > 0 && list.get(position).getTextContent() != null) {
                ItemListAdapter adapter = new ItemListAdapter(context, list.get(position).getTextContent());
                adapter.setTextSize(width, hight);
                viewHold.item_list.setAdapter(adapter);

                RelativeLayout.LayoutParams frame = new RelativeLayout.LayoutParams(
                        DeviceUtil.dpToPx(context,list.get(position).getTextContent().size() * 100),
                        RelativeLayout.LayoutParams.MATCH_PARENT);
                viewHold.item_list.setLayoutParams(frame);
            }
            convertView.setTag(viewHold);
        }
        return convertView;
    }

    static class ViewHold {
        RecyclerView item_list;
    }

}
