package com.cxx.Test;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

public class TestJson {
    public static void main(String[] args) {
        try {
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://localhost:8080/test/test06");
        //设置请求头
        post.setHeader("Content-Type", "application/json");
        String body = "{\"Key\": \"cxx\",\"Secret\": \"123456\"}";
        //设置请求体
        post.setEntity(new StringEntity(body));
        //获取返回信息
        HttpResponse response = client.execute(post);
        String result = response.toString();
        } catch (Exception e) {
        System.out.println("接口请求失败"+e.getStackTrace());
        }
    }
}
