package com.github.desfate.vhscrollview.models;

/**
 * 标题数据
 */

public class TitleModel {
    private String goodsName;
    private String goodsCode;

    public TitleModel(String goodsName, String goodsCode) {
        this.goodsName = goodsName;
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }
}
