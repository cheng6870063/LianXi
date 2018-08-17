package com.example.activitydemo.controller;

import com.example.activitydemo.service.LeaveService;
import com.example.activitydemo.service.TestLeaveService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;


@RestController
public class LeaveController {

	private static Logger logger = LoggerFactory.getLogger(LeaveController.class);

	@Autowired
	private LeaveService leaveService;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private TestLeaveService testLeaveService;

	/**
	 * 发起申请，新增信息
	 * @param msg
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/addLeaveInfo") 
	public String addLeaveInfo(String msg,HttpServletRequest request,Model model) {
		leaveService.addLeaveAInfo(msg);
		return "新增成功...";
	}
	
	/**
	 * 查询当前用户的任务列表
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping("/getTaskByUserId")
	public Object getTaskByUserId(String userId,HttpServletRequest request) {
		//System.out.println();
		return leaveService.getByUserId(userId);
	}
	
	/**
	 * 处理完成任务
	 * @param taskId
	 * @param userId
	 * @param audit
	 * @param request
	 * @return
	 */
	@RequestMapping("/completeTask")
	public String completeTask(String taskId,String userId,String audit,HttpServletRequest request) {
		leaveService.completeTaskByUser(taskId, userId, audit);
		return "审批完成...";
	}
	
	
	@RequestMapping("/showImg")
	public void showImg(String deploymentId,HttpServletRequest request,HttpServletResponse response) {
	
		try {
			InputStream inputStream = leaveService.findProcessPic(deploymentId);
			byte[] b = new byte[1024];
			int len = -1;
			while((len = inputStream.read(b, 0, 1024)) != -1) {
				response.getOutputStream().write(b, 0, len);
			}
		} catch (IOException e) {
			logger.error("读取流程图片出错:{" + e + "}");
		}
		
	}

	/*
	部署流程定义
	 */
	@RequestMapping("/deployment")
	public void deployment(String deploymentId,HttpServletRequest request,HttpServletResponse response) {

		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
		ProcessDefinition result = query.deploymentId(deploymentId).singleResult();
		String name = result.getDiagramResourceName();
		InputStream inputStream = repositoryService.getResourceAsStream(result.getDeploymentId(), name);
		/*ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
		List<ProcessDefinition> list = query.list();
		ProcessDefinition result = list.get(0);
 		String name = result.getDiagramResourceName();
		InputStream inputStream = repositoryService.getResourceAsStream(result.getDeploymentId(), name);*/
		byte[] b = new byte[1024];
		int len = -1;
		try {
			while((len = inputStream.read(b, 0, 1024)) != -1) {
                response.getOutputStream().write(b, 0, len);
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
