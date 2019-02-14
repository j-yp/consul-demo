package com.example.consul.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.consul.entity.User;
import com.example.consul.mapper.UserMapper;
import com.example.consul.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUserById(String id) {
		return userMapper.selectById(id);
	}

}
