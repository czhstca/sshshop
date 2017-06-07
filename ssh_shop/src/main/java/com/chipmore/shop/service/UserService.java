package com.chipmore.shop.service;

import com.chipmore.shop.domain.User;

public interface UserService {

	public User findByUsername(String username);
}
