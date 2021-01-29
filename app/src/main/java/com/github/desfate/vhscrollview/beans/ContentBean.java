package com.github.desfate.vhscrollview.beans;

/**
 * Created by huangyan on 2019/2/28.
 */

public class ContentBean {
    private String content;
    private int color = 0;

    public ContentBean(String content, int color) {
        this.content = content;
        this.color = color;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
