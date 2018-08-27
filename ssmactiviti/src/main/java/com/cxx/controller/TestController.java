package com.cxx.controller;

import com.cxx.bean.Sale;
import com.cxx.bean.SaleItem;
import com.cxx.bean.User;
import com.cxx.service.UserService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 2YSP on 2018/8/1.
 */
@RestController
public class TestController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        /*Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("rule/Sale.drl")
                .addClasspathResource("processes/SaleRule.bpmn").deploy();*/


        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId("2501").singleResult();
        ProcessInstance pi = runtimeService.startProcessInstanceById(processDefinition.getId());
        //创建事实实例,符合周六周日打九折
        Sale s1 = new Sale("001",createDate("2013-05-05"));
        SaleItem item1 = new SaleItem("矿泉水",new BigDecimal(5),new BigDecimal(4));
        s1.addItem(item1);

        //满一百打八折
        Sale s2 = new Sale("002",createDate("2013-06-19"));
        SaleItem item2 = new SaleItem("爆米花",new BigDecimal(20),new BigDecimal(5));
        s2.addItem(item2);
        //满200打七折
        Sale s3 = new Sale("003",createDate("2013-06-19"));
        SaleItem item3 = new SaleItem("可乐一箱",new BigDecimal(70),new BigDecimal(3));
        s3.addItem(item3);

        //星期天满200
        Sale s4 = new Sale("004",createDate("2013-05-05"));
        SaleItem item4 = new SaleItem("爆米花一箱",new BigDecimal(80),new BigDecimal(3));
        s4.addItem(item4);

        Map<String,Object> vars = new HashMap<String, Object>();
        vars.put("sale1",s1);
        vars.put("sale2",s2);
        vars.put("sale3",s3);
        vars.put("sale4",s4);
        //查找任务
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();

        taskService.complete(task.getId(),vars);
        return "ok";
    }

    private  Date createDate(String s) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/add")
    public String addUser(){
        User user = new User();
        user.setName("zhangsan");
        user.setAge(11);
        userService.add(user);
        return "ok";
    }
}