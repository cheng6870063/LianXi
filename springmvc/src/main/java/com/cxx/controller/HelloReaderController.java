package com.cxx.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value="/hellos")
public class HelloReaderController {

    // 添加slf4j日志实例对象
    final static Logger logger = LoggerFactory.getLogger(HelloReaderController.class);

    @RequestMapping(value = "/hellos", method = GET)
    public ModelAndView sayHello() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "Hello Reader!");
        mv.setViewName("helloReader");
        // 输出日志
        logger.info("测试：{}", "输出日志");
        return mv;
    }
}
