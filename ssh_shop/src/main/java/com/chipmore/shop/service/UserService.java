package com.chipmore.shop.service;

import com.chipmore.shop.domain.User;

public interface UserService {

	//按用户名查找用户
	public User findByUsername(String username);
	
	//保存用户信息
	public void saveUser(User user);
}
