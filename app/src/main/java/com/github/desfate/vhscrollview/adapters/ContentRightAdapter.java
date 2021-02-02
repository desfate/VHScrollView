package com.github.desfate.vhscrollview.adapters;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

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

            viewHold.head_right_layout = convertView.findViewById(R.id.head_right_layout);

            int textNum = 0;

            if (list != null && list.size() > 0 && list.get(0).getTextContent() != null) {
                textNum = list.get(0).getTextContent().size();
            }

            for (int i = 0; i < textNum; i++) {
                TextView textView = new TextView(context);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(DeviceUtil.dpToPx(context, width)
                        , DeviceUtil.dpToPx(context, hight));  //设置宽高 （这个地方 理想的情况是根据手机屏幕宽度均分  晚点来弄）
                textView.setLayoutParams(lp);
                textView.setTextSize(DeviceUtil.dpToPx(context, 4));
//                if(Config.Chart_Color){
//                    textView.setTextColor(context.getResources().getColor(R.color.p_global_black_color));
//                }
                textView.setPadding(40,0,0,0);
                textView.setGravity(Gravity.CENTER_VERTICAL);
//                TextViewCompat.setAutoSizeTextTypeWithDefaults(textView, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);  //自动变换
//                TextViewCompat.setAutoSizeTextTypeWithDefaults(textView, DeviceUtil.dpToPx(5));
                viewHold.head_right_layout.addView(textView);
            }
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }

        ArrayList<ContentBean> content = list.get(position).getTextContent();
        if (content != null) {
            for (int i = 0; i < content.size(); i++) {
                TextView tv = ((TextView) viewHold.head_right_layout.getChildAt(i));
                tv.setText(content.get(i).getContent());  //写入数据
                if (content.get(i).getColor() == 1) {
                    tv.setTextColor(context.getResources().getColor(R.color.p_up_color));
                } else if (content.get(i).getColor() == -1) {
                    tv.setTextColor(context.getResources().getColor(R.color.p_down_color));
                }
            }
        }
        content = null;

        return convertView;
    }

    static class ViewHold {

        LinearLayout head_right_layout;
    }

}
