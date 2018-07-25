package com.cxx.Test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;

public class TestFile {
    public static void main(String[] args)  {
        try {
            //HttpClient httpclient = new DefaultHttpClient();
            CloseableHttpClient httpclient = HttpClients.createDefault();
            //HttpPost httppost = new HttpPost("http://localhost:8080/test/test03");
            //HttpPost httppost = new HttpPost("http://localhost:8080/test/test04");
            HttpPost httppost = new HttpPost("http://localhost:8080/test/test05");
            FileBody bin = new FileBody(new File("E:\\IIS-FB\\cxx.txt"));
            StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);
            HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("file", bin).addPart("comment", comment).build();
            httppost.setEntity(reqEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    System.out.println("Response content length: " + resEntity.getContentLength());
                }
                EntityUtils.consume(resEntity);
            } finally {
                response.close();
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
