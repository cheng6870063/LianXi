package com.cxx.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
@RequestMapping("/demo")
@Api("/demo")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Object login(@RequestBody Map<String, Object> param) throws Exception {
        return param;
    }

    @ApiOperation(value = "查询用户接口", notes = "根据用户ID查询用户", httpMethod = "GET")
    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getUser(
            @ApiParam(name = "id", value = "用户ID", required = true) @PathVariable("id") String userId) {
        logger.info("参数", userId);
        String str = "测试swagger";
        String string = null;
        try {
            string = new String(str.trim().getBytes("ISO-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return string;
    }

    @RequestMapping(value = "testGetParameter01", method = RequestMethod.GET)
    @ResponseBody
    public String testGetParameter01(String str1,String str2) throws Exception {
        /*String str1 = request.getParameter("str1");
        String str2 = request.getParameter("str2");*/
        return str1 + str2;
    }

    @RequestMapping(value = "testGetParameter02/{str1}{str2}", method = RequestMethod.GET)
    @ResponseBody
    public String testGetParameter02( @PathVariable String str1, @PathVariable String str2) throws Exception {
        return str1 + str2;
    }

    @RequestMapping(value = "testPostParameter01", method = RequestMethod.POST)
    @ResponseBody
    public String testPostParameter01(String str1,String str2) throws Exception {
        return str1 + str2;
    }

    @RequestMapping(value = "testPostParameter02", method = RequestMethod.POST)
    @ResponseBody
    public String testPostParameter02(String str1, MultipartFile file) throws Exception {
        return str1;
    }

    @RequestMapping(value = "testPostParameter03", method = RequestMethod.POST)
    @ResponseBody
    public String testPostParameter03(@RequestBody String str) throws Exception {
        return str;
    }

    @RequestMapping(value = "testPostParameter04", method = RequestMethod.POST)
    @ResponseBody
    public String testPostParameter04(@RequestBody User user) throws Exception {
        return user.toString();
    }

}
