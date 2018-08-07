package com.example.activitydemo.service;

import com.example.activitydemo.entity.LeaveInfo;

import java.util.List;


public interface LeaveService {

	/**
	 * 新增一条请假单记录
	 * @param
	 */
	void addLeaveAInfo(String msg);
	/**
	 * 查询待办流程
	 * @param userId
	 * @return
	 */
	List<LeaveInfo> getByUserId(String userId);
	
	/**
	 * 完成任务
	 * @param taskId
	 * @param userId
	 * @param audit
	 */
	void completeTaskByUser(String taskId, String userId, String audit);
}
