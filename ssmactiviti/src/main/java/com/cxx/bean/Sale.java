package com.cxx.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 销售单对象
 * Created by 2YSP on 2018/7/31.
 */
public class Sale implements Serializable {
    /**
     * 销售单号
     */
    private String saleCode;
    /**
     * 销售日期
     */
    private Date date;
    /**
     * 销售明细
     */
    private List<SaleItem> items;
    /**
     * 折扣
     */
    private BigDecimal discount = new BigDecimal(1);

    public Sale(String saleCode, Date date) {
        this.saleCode = saleCode;
        this.date = date;
        this.items = new ArrayList<SaleItem>();
    }


    //返回日期为星期几
    public int getDayOfWeek(){
        Calendar c = Calendar.getInstance();
        c.setTime(this.date);
        return  c.get(Calendar.DAY_OF_WEEK);
    }

    //返回销售总金额（折扣前）
    public BigDecimal getTotal(){
        BigDecimal total = BigDecimal.ZERO;
        for(SaleItem item : items){
            BigDecimal itemTotal = item.getPrice().multiply(item.getAmount());
            total = total.add(itemTotal);
        }
        total = total.setScale(2,BigDecimal.ROUND_HALF_UP);
        return total;
    }

    //返回优惠后的总金额
    public BigDecimal getDiscountTotal(){
        BigDecimal total = getTotal();
        total = total.multiply(this.discount).setScale(2,BigDecimal.ROUND_HALF_UP);
        return total;
    }

    public String getSaleCode() {
        return saleCode;
    }

    public void setSaleCode(String saleCode) {
        this.saleCode = saleCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<SaleItem> getItems() {
        return items;
    }

    public void setItems(List<SaleItem> items) {
        this.items = items;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void addItem(SaleItem item){
        items.add(item);
    }
}
