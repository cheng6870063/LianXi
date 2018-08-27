package com.cxx.service;

import com.cxx.bean.User;
import com.cxx.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 2YSP on 2018/8/1.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void add(User user){
        userDao.insert(user);
    }
}