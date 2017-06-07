package com.chipmore.shop.dao;

import com.chipmore.shop.domain.User;

public interface UserDao {

	public User findByUsername(String username);
}
