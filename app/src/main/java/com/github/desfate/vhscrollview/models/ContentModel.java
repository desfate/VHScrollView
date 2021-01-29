package com.github.desfate.vhscrollview.models;

import com.github.desfate.vhscrollview.beans.ContentBean;

import java.util.ArrayList;

/**
 * 正文数据格式
 */
public class ContentModel {

    private ArrayList<ContentBean> textContent;  //一行正文要填入的数据

    public ArrayList<ContentBean> getTextContent() {
        return textContent;
    }

    public ContentModel(ArrayList<ContentBean> textContent) {
        this.textContent = textContent;
    }
    public void setTextContent(ArrayList<ContentBean> textContent) {
        this.textContent = textContent;
    }
}
