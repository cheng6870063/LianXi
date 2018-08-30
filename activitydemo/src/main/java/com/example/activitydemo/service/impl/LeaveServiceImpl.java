package com.example.activitydemo.service.impl;

import com.example.activitydemo.dao.LeaveMapper;
import com.example.activitydemo.entity.LeaveInfo;
import com.example.activitydemo.service.LeaveService;
import com.example.activitydemo.service.TestLeaveService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class LeaveServiceImpl implements LeaveService {

	@Autowired
	private TestLeaveService testLeaveService;
	@Autowired
	private LeaveMapper leaveMapper;
	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private RepositoryService repositoryService;
	@Override
	public void addLeaveAInfo(String msg) {
		LeaveInfo leaveInfo = new LeaveInfo();
		leaveInfo.setLeaveMsg(msg);
		String id = UUID.randomUUID().toString();
		leaveInfo.setId(id);
		//新增一条记录至数据库中
		leaveMapper.insert(leaveInfo);
		//启动流程引擎
		testLeaveService.startProcess(id);
	}

	@Override
	public List<LeaveInfo> getByUserId(String userId) {
		ArrayList<LeaveInfo> leaveInfoList = new ArrayList<>();
		List<Task> list = testLeaveService.findTaskByUserId(userId);
		for (Task task : list) {
			ProcessInstance result = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
			//获得业务流程的bussinessKey
			String businessKey = result.getBusinessKey();
			LeaveInfo leaveInfo = leaveMapper.getById(businessKey);
			leaveInfo.setTaskId(task.getId());
			leaveInfoList.add(leaveInfo);
		}
		return leaveInfoList;
	}

	@Override
	public void completeTaskByUser(String taskId, String userId, String audit) {
		testLeaveService.completeTaskByUser(taskId, userId, audit);
	}

	@Override
	public InputStream findProcessPic(String deploymentId) {
		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
		ProcessDefinition result = query.deploymentId(deploymentId).singleResult();
		String name = result.getDiagramResourceName();
		InputStream inputStream = repositoryService.getResourceAsStream(result.getDeploymentId(), name);
		return inputStream;
	}

	@Override
	public void updateLeaveInfo(LeaveInfo entity) {
		leaveMapper.update(entity);
	}

}
