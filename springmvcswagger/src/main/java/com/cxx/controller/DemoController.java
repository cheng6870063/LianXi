package com.cxx.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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

}
