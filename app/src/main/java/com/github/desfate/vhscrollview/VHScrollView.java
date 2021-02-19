package com.github.desfate.vhscrollview;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.desfate.vhscrollview.adapters.ContentRightAdapter;
import com.github.desfate.vhscrollview.adapters.LeftHeaderAdapter;
import com.github.desfate.vhscrollview.models.ContentModel;
import com.github.desfate.vhscrollview.models.TitleModel;
import com.github.desfate.vhscrollview.tools.DeviceUtil;
import com.github.desfate.vhscrollview.widgets.SyncHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * 同时支持水平滑动和垂直滑动的view
 */

public class VHScrollView extends FrameLayout {

    private int ViewState = 1;  //记录view屏幕状态  1：竖屏  2：横屏

    //需要三组数据
    private List<TitleModel> leftTitle = new ArrayList<>();  //左侧标题
    private List<String> rightTitle = new ArrayList<>(); //右侧标题
    private List<ContentModel> models = new ArrayList<>(); //正文数据

    private LinearLayout left_title_container;
    private LinearLayout left_container;

    private LinearLayout right_title_container;  //右侧标题布局
    private ListView left_container_listview;//左侧正文标题布局
    private ListView contentListView;    //正文le_
    private LinearLayout title_layout;   //标题布局
    private ScrollView scrollView;   //滑动控件
    private TextView left_title;//左侧标题名称

    /********************  可变参数 *********************/
    private int title_width = 150;  //默认宽度  标题宽度
    private int title_hight = 50;   //默认高度  标题高度
    private int content_width = 100;  //默认宽度  正文宽度
    private int content_hight = 50;   //默认高度  正文高度
    private int right_title_width = 100; //默认宽度 右侧标题宽度
    private int right_title_hight = 40;  //默认高度 右侧标题高度

    private float contentWeight = (float) 2.5;  //默认比例 正文比例 越小正文越宽
    private float titleWeight = (float) 2.5;  //标题比例  越小标题越大

    private int background_color = R.color.p_global_write_color;
    private int title_color = R.color.p_global_write_color;
    private int title_value_color = R.color.p_title_color;


    private SyncHorizontalScrollView titleHorsv, contentHorsv; //联动部分

    public VHScrollView(@NonNull Context context) {
        super(context);
    }

    public VHScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VHScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initLayout() {
        inflate(getContext(), R.layout.vhscroll_main_lay, this);
        findView();
        init();

    }

    private void findView() {
        right_title_container = findViewById(R.id.right_title_container);  //右侧标题  需要插入
        left_container_listview = findViewById(R.id.left_container_listview);
        titleHorsv = findViewById(R.id.title_horsv);
        contentHorsv = findViewById(R.id.content_horsv);
        contentListView = findViewById(R.id.right_container_listview);
        title_layout = findViewById(R.id.title_layout);
        scrollView = findViewById(R.id.scrollView);
        left_title = findViewById(R.id.left_title);

        left_title_container = findViewById(R.id.left_title_container);
        left_container = findViewById(R.id.left_container);

        scrollView.setBackground(getResources().getDrawable(background_color));
    }

    public void setLeft_Title(String str) {
        left_title.setText(str);
        left_title.setTextSize(13);
    }

    /**
     * 设置左侧标题大小
     *
     * @param width 标题宽度
     * @param hight 标题高度
     */
    public void setLiftTitleSize(int width, int hight) {
        this.title_width = width;
        this.title_hight = hight;
    }

    /**
     * 设置右侧标题大小
     *
     * @param width
     * @param hight
     */
    public void setRightTitleSize(int width, int hight) {
        this.right_title_width = width;
        this.right_title_hight = hight;
    }

    /**
     * 设置正文大小
     *
     * @param width
     * @param hight
     */
    public void setContentSize(int width, int hight) {
        this.content_hight = hight;
        this.content_width = width;
    }

    /**
     * 设置正文比例
     *
     * @param weight
     */
    public void setContentWeight(float weight) {
        contentWeight = weight;
    }

    /**
     * 设置标题颜色
     *
     * @param color
     */
    public void setTitleColor(int color) {
        this.title_color = color;
    }

    private void init() {

//        TextViewCompat.setAutoSizeTextTypeWithDefaults(left_title, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);  //自动变换
        LinearLayout.LayoutParams titleparams = (LinearLayout.LayoutParams) left_title_container.getLayoutParams();
        LinearLayout.LayoutParams title = (LinearLayout.LayoutParams) left_container.getLayoutParams();
        titleparams.weight = titleWeight;
        title.weight = titleWeight;
        left_title_container.setLayoutParams(titleparams);
        left_container.setLayoutParams(title);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) title_layout.getLayoutParams();
        params.width = DeviceUtil.dpToPx(getContext(), title_width);
        params.height = DeviceUtil.dpToPx(getContext(),40);
        title_layout.setLayoutParams(params);

        //设置标题背景颜色
        title_layout.setBackgroundColor(getResources().getColor(title_color));
        right_title_container.setBackgroundColor(getResources().getColor(title_color));

        // 设置两个水平控件的联动
        titleHorsv.setScrollView(contentHorsv);
        contentHorsv.setScrollView(titleHorsv);

        // 添加左边内容数据
//        left_container_listview.setBackgroundColor(Color.YELLOW);
        LeftHeaderAdapter adapter = new LeftHeaderAdapter(getContext(), leftTitle);
        adapter.setSize(title_hight, title_width);  //设置宽高
        left_container_listview.setAdapter(adapter);
        setListViewHeightBasedOnChildren(left_container_listview);


        // 添加右侧标题数据
        for (int i = 0; i < rightTitle.size(); i++) {
            TextView textView = new TextView(getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(DeviceUtil.dpToPx(getContext(), right_title_width)
                    , DeviceUtil.dpToPx(getContext(), right_title_hight));  //设置宽高 （这个地方 理想的情况是根据手机屏幕宽度均分  晚点来弄）
            textView.setLayoutParams(lp);
            textView.setTextSize(DeviceUtil.dpToPx(getContext(),4));
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setPadding(50,0,0,0);
            textView.setText(rightTitle.get(i));
            textView.setTextColor(getResources().getColor(title_value_color));
            right_title_container.addView(textView);
        }


        // 添加正文内容数据
        ContentRightAdapter myRightAdapter = new ContentRightAdapter(getContext(), models);
        myRightAdapter.setSize(content_width, content_hight);
        contentListView.setAdapter(myRightAdapter);
        setListViewHeightBasedOnChildren(contentListView);


        ViewTreeObserver vto = scrollView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                scrollView.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) left_container_listview.getLayoutParams();
                if (params.height < scrollView.getHeight()) {
                    params.height = scrollView.getHeight();
                    params.weight = contentWeight;
                    params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    left_container_listview.setLayoutParams(params);

                    LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) contentHorsv.getLayoutParams();
                    params2.height = scrollView.getHeight();
                    params2.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    params2.weight = 1;
                    contentHorsv.setLayoutParams(params2);

                    LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) contentListView.getLayoutParams();
                    params3.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    params3.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    contentListView.setLayoutParams(params3);

                }
            }
        });
    }

    public void resetListHight() {
        //判断当前状态
        int flag = getContext().getResources().getConfiguration().orientation;

        if (flag == Configuration.ORIENTATION_LANDSCAPE) {//横屏
            ViewState = 1;
        } else if (flag == Configuration.ORIENTATION_PORTRAIT) {
            ViewState = 2;
        }


        ViewTreeObserver vto = scrollView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
//                scrollView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) left_container_listview.getLayoutParams();
//                if (ViewState == 1) {  //竖屏
//                    if (params.height < scrollView.getHeight()) {
//                        params.height = scrollView.getHeight();
//                        params.weight = contentWeight;
//                        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
//                        left_container_listview.setLayoutParams(params);
//
//                        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) contentHorsv.getLayoutParams();
//                        params2.height = scrollView.getHeight();
//                        params2.width = ViewGroup.LayoutParams.MATCH_PARENT;
//                        params2.weight = 1;
//                        contentHorsv.setLayoutParams(params2);
//
//                        LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) contentListView.getLayoutParams();
//                        params3.height = ViewGroup.LayoutParams.MATCH_PARENT;
//                        params3.width = ViewGroup.LayoutParams.MATCH_PARENT;
//                        contentListView.setLayoutParams(params3);
//                    }
//                } else {
//                    if (params.width < scrollView.getWidth()) {
//                        params.width = scrollView.getWidth();
//                        params.weight = contentWeight;
//                        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
//                        left_container_listview.setLayoutParams(params);
//
//                        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) contentHorsv.getLayoutParams();
//                        params2.height = scrollView.getHeight();
//                        params2.width = ViewGroup.LayoutParams.MATCH_PARENT;
//                        params2.weight = 1;
//                        contentHorsv.setLayoutParams(params2);
//
//                        LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) contentListView.getLayoutParams();
//                        params3.height = ViewGroup.LayoutParams.MATCH_PARENT;
//                        params3.width = ViewGroup.LayoutParams.MATCH_PARENT;
//                        contentListView.setLayoutParams(params3);
//                    }
//                }
            }
        });
    }


    public void onRefresh(List<ContentModel> models) { //刷新正文数据
        this.models = models;
        ((ContentRightAdapter) contentListView.getAdapter()).setData(models);
    }


    //校准位置
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public List<TitleModel> getLeftTitle() {
        return leftTitle;
    }

    public void setLeftTitle(List<TitleModel> leftTitle) {
        this.leftTitle = leftTitle;
    }

    public List<String> getRightTitle() {
        return rightTitle;
    }

    public void setRightTitle(List<String> rightTitle) {
        this.rightTitle = rightTitle;
    }

    public List<ContentModel> getModels() {
        return models;
    }

    public void setModels(List<ContentModel> models) {
        this.models = models;
    }

    public float getTitleWeight() {
        return titleWeight;
    }

    public void setTitleWeight(float titleWeight) {
        this.titleWeight = titleWeight;
    }
}
