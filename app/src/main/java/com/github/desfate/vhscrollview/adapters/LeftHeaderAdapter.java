package com.github.desfate.vhscrollview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.widget.TextViewCompat;

import com.github.desfate.vhscrollview.R;
import com.github.desfate.vhscrollview.models.TitleModel;
import com.github.desfate.vhscrollview.tools.DeviceUtil;

import java.util.List;

/**
 * 左侧头部适配器
 */
public class LeftHeaderAdapter extends BaseAdapter {

    private Context context;
    private List<TitleModel> list;
    private int hight;
    private int width;

    public LeftHeaderAdapter(Context context, List<TitleModel> list) {
        super();
        this.context = context;
        this.list = list;
    }

    public void setSize(int hight, int width) {
        this.hight = hight;
        this.width = width;
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
        ViewHold hold;
        if (convertView == null) {
            hold = new ViewHold();
            convertView = LayoutInflater.from(context).inflate(R.layout.hv_head_left_title, null);
            hold.layout = convertView.findViewById(R.id.nameLay);
            hold.goodsName = convertView.findViewById(R.id.left_container_textview);
            hold.goodsCode = convertView.findViewById(R.id.code_textview);
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) hold.layout.getLayoutParams();
            params.width = DeviceUtil.dpToPx(context, width);
            params.height = DeviceUtil.dpToPx(context, hight);
            hold.layout.setLayoutParams(params);
            convertView.setTag(hold);
        } else {
            hold = (ViewHold) convertView.getTag();
        }
        TextViewCompat.setAutoSizeTextTypeWithDefaults(hold.goodsName, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);  //自动变换
        hold.goodsName.setText(list.get(position).getGoodsName());
        if (list.get(position).getGoodsCode().equals("")) {
            hold.goodsCode.setVisibility(View.GONE);
        } else {
            hold.goodsCode.setText(list.get(position).getGoodsCode());
        }
        return convertView;
    }

    static class ViewHold {
        LinearLayout layout;
        TextView goodsName;
        TextView goodsCode;
    }

}
