package com.example.activitydemo.dao;

import com.example.activitydemo.entity.LeaveInfo;

import java.util.List;


public interface LeaveMapper {

	int getCount();
	
	void insert(LeaveInfo entity);
	
	void delete(String id);
	
	LeaveInfo getById(String id);
	
	List<LeaveInfo> getList();
	
	void update(LeaveInfo entity);
}
