package com.cxx.controller;


import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Iterator;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "/test01", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String test01(String account,String password) {
        System.out.println(account + password);
        return "hello";
    }

    @RequestMapping(value = "/test02", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String test02(HttpServletRequest request) {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        System.out.println(account + password);
        return "hello";
    }

    @RequestMapping(value = "/test03", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String testfile1(MultipartFile file) throws IOException {
        //设置上传文件的保存地址目录
        String dirPath="F:\\fileUpload\\";
        File files =new File(dirPath);
        //如果保存文件的地址不存在，就先创建目录
        if(!files.exists()){
            files.mkdirs();
        }
        //获取上传文件的原始名称
        String originalFilename = file.getOriginalFilename();
       //使用MultipartFile接口的方法完成文件上传到指定位置
        file.transferTo(new File(dirPath+originalFilename));
        return "hello";
    }

    @RequestMapping(value = "/test04", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String testfile2(MultipartFile file) throws IOException {
        String dirPath="F:\\fileUpload1\\";
        File files =new File(dirPath);
        //如果保存文件的地址不存在，就先创建目录
        if(!files.exists()){
            files.mkdirs();
        }
        //获取输出流
        OutputStream os=new FileOutputStream("F:\\fileUpload1\\" +file.getOriginalFilename());
        //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
        InputStream is=file.getInputStream();
        int temp;
        //一个一个字节的读取并写入
        while((temp=is.read())!=(-1))
        {
            os.write(temp);
        }
        os.flush();
        os.close();
        is.close();
        return "hello";
    }

    @RequestMapping(value = "/test05", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String testfile3(HttpServletRequest request) throws IOException {
        long  startTime=System.currentTimeMillis();
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();

            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file != null)
                {
                    File files =new File("F:\\springUpload\\");
                    //如果保存文件的地址不存在，就先创建目录
                    if(!files.exists()){
                        files.mkdirs();
                    }
                    String path="F:\\springUpload\\"+file.getOriginalFilename();
                    //上传
                    file.transferTo(new File(path));
                }

            }

        }
        long  endTime=System.currentTimeMillis();
        System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");
        return "hello";
    }


    @RequestMapping(value = "/test06", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String testJson(HttpServletRequest request) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
        (ServletInputStream) request.getInputStream(), "utf-8"));
        StringBuffer sb = new StringBuffer("");
        String temp;
        while ((temp = br.readLine()) != null) {
                  sb.append(temp);
        }
        br.close();
        String acceptjson = sb.toString();
        JSONObject jo = JSONObject.fromObject(acceptjson);
        return "hello";
    }
}
