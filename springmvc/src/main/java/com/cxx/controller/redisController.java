package com.cxx.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(value="/member")
public class redisController {

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping(value={"/delete"},method={RequestMethod.GET})
    @ResponseBody
    public String delete(HttpServletRequest request){
        redisTemplate.delete("gender");
        return "success";
    }

    @RequestMapping(value={"/add"},method={RequestMethod.GET})
    @ResponseBody
    public String add(HttpServletRequest request){
        //操作字符
        redisTemplate.opsForValue().set("gender", "女");
        Map<String,String> maps = new HashMap<String, String>();
        maps.put("multi1","multi1");
        maps.put("multi2","multi2");
        maps.put("multi3","multi3");
        redisTemplate.opsForValue().multiSet(maps);
        //操作List
        redisTemplate.opsForList().leftPush("list","a");
        redisTemplate.opsForList().leftPush("list","b");
        return "success";
    }

    @RequestMapping(value={"/get"},method={RequestMethod.GET})
    @ResponseBody
    public String get(HttpServletRequest request){
       String gender= (String) redisTemplate.opsForValue().get("gender");
       System.out.println(gender);
       Set<String> result = redisTemplate.keys("*multi*");
       System.out.println(result.size());
       return "success";
    }
}
