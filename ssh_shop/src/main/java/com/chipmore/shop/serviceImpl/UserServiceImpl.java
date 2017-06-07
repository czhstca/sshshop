package com.chipmore.shop.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chipmore.shop.dao.UserDao;
import com.chipmore.shop.domain.User;
import com.chipmore.shop.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	private UserDao userDao;
	
	@Resource(name="userDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUsername(username);
	}

}
