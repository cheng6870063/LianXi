package com.cxx.Test;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class TestFiles {
    public static void main(String[] args)  {
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            CloseableHttpResponse httpResponse = null;
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000000).build();
            HttpPost httpPost = new HttpPost("http://localhost:8080/test/test03");
            httpPost.setConfig(requestConfig);
            //当设置了setSocketTimeout参数后，以下代码上传PDF不能成功，将setSocketTimeout参数去掉后此可以上传成功。上传图片则没有个限制
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            File file = new File("E:\\IIS-FB\\cxx.txt");
            multipartEntityBuilder.addBinaryBody("file",file);
            multipartEntityBuilder.addBinaryBody("file1",file);
            multipartEntityBuilder.addTextBody("comment", "this is comment");
            HttpEntity httpEntity = multipartEntityBuilder.build();
            httpPost.setEntity(httpEntity);

            httpResponse = httpClient.execute(httpPost);
            HttpEntity responseEntity = httpResponse.getEntity();
            int statusCode= httpResponse.getStatusLine().getStatusCode();
            if(statusCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));
                StringBuffer buffer = new StringBuffer();
                String str = "";
                while (!reader.readLine().isEmpty()) {
                    buffer.append(str);
                }
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
