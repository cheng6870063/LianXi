package com.cxx.Test;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/*
最简单的Post请求
 */
public class TestPost {
    public static void main(String[] args) throws Exception {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("account", "cxx"));
        formparams.add(new BasicNameValuePair("password", "123456"));
        HttpEntity reqEntity = new UrlEncodedFormEntity(formparams, "utf-8");
        /*
        如果有乱码,可以偿试使用 StringEntity 来替换HttpEntity:
        StringEntity content =new StringEntity(formparams.toString(), Charset.forName("UTF-8"));// 第二个参数，设置后才会对，内容进行编码
        content.setContentType("application/soap+xml; charset=UTF-8");
        content.setContentEncoding("UTF-8");
        httppost.setEntity(content);
         */

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)//一、连接超时：connectionTimeout-->指的是连接一个url的连接等待时间
                .setSocketTimeout(5000)// 二、读取数据超时：SocketTimeout-->指的是连接上一个url，获取response的返回等待时间
                .setConnectionRequestTimeout(5000)
                .build();

        HttpClient client = new DefaultHttpClient();
        //HttpPost post = new HttpPost("http://localhost:8080/test/test01");
        //post请求后拼接参数也能接收到
        //HttpPost post = new HttpPost("http://localhost:8080/test/test02?account=ddd");
        HttpPost post = new HttpPost("http://localhost:8080/test/test02");
        post.setEntity(reqEntity);
        post.setConfig(requestConfig);
        HttpResponse response = client.execute(post);

        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity resEntity = response.getEntity();
            String message = EntityUtils.toString(resEntity, "utf-8");
            System.out.println(message);
        } else {
            System.out.println("请求失败");
        }
    }

}
