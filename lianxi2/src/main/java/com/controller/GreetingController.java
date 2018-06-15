package com.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.bean.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    //创建具有初始值 0 的新 AtomicLong
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),  //以原子方式将当前值加 1
               String.format(template, name));//%S像是一个通配符，把参数一个一个按顺序填到对应位置
    }
}
