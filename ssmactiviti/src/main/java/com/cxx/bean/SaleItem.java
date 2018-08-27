package com.cxx.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *  销售明细
 * Created by 2YSP on 2018/7/31.
 */
public class SaleItem implements Serializable {
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品单价
     */
    private BigDecimal price;

    /**
     * 数量
     */
    private BigDecimal amount;

    public SaleItem(String goodsName, BigDecimal price, BigDecimal amount) {
        this.goodsName = goodsName;
        this.price = price;
        this.amount = amount;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
