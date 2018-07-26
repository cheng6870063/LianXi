package com.cxx.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.log4j.Logger;

@Controller
@RequestMapping(value = "/hello", method = RequestMethod.GET)
public class HelloController {
    private static Logger logger = Logger.getLogger(HelloController.class);
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        model.addAttribute("msg", "Spring MVC Hello World");
        model.addAttribute("name", "yuntao");
        logger.info("访问首页");
        logger.debug("ccccccccc");
        return "hello";
    }
}