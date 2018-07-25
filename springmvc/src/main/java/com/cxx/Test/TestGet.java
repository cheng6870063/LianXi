package com.cxx.Test;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class TestGet {
    public static void main(String[] args) {
        try {
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("account", "cxx"));
            //System.out.println(formparams.toString());
            String str = "";
            //转换为键值对
            str = EntityUtils.toString(new UrlEncodedFormEntity(formparams, Consts.UTF_8));
            System.out.println(str);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000)//一、连接超时：connectionTimeout-->指的是连接一个url的连接等待时间
                    .setSocketTimeout(5000)// 二、读取数据超时：SocketTimeout-->指的是连接上一个url，获取response的返回等待时间
                    .setConnectionRequestTimeout(5000)
                    .build();

            HttpClient client = new DefaultHttpClient();

            // 创建httpget.
            HttpGet httpget = new HttpGet("http://localhost:8080/test/test01" + "?" + str);
            httpget.setConfig(requestConfig);
            HttpResponse response = client.execute(httpget);

            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity resEntity = response.getEntity();
                String message = EntityUtils.toString(resEntity, "utf-8");
                System.out.println(message);
            } else {
                System.out.println("请求失败");
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
