package com.cxx.service;

import com.cxx.bean.Sale;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.util.Collection;

public class SaleJavaDelegate implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        Collection sales = (Collection) execution.getVariable("saleResults");
        System.out.println("输出处理结果：");
        for(Object obj : sales){
            Sale sale  = (Sale) obj;
            System.out.println("销售单："+sale.getSaleCode()+" ,原件："+sale.getTotal()+
                    " ,优惠后："+sale.getDiscountTotal()+" ,折扣:"+sale.getDiscount());

        }
    }
}
