package com.chipmore.shop.dao;

import com.chipmore.shop.domain.User;

public interface UserDao {

	public User findByUsername(String username);
	
	public void saveUser(User user);

	public User findUserByCode(String code);

	public void updateUser(User user);

	public User login(User user);
}
