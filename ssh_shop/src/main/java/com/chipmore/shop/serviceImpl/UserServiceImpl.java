package com.chipmore.shop.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chipmore.shop.dao.UserDao;
import com.chipmore.shop.domain.User;
import com.chipmore.shop.service.UserService;
import com.chipmore.shop.utils.MailUtils;
import com.chipmore.shop.utils.UUIDUtils;

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

	@Override
	public void saveUser(User user) {
		//暂时自己设置验证码
		user.setState(0);   //0.用户未激活  1.用户已激活
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();   //生成随机字符串
		user.setCode(code);
		userDao.saveUser(user);
		//发送激活邮件
		MailUtils.sendMail("aaa@shop.com", code);
	}

	@Override
	public User findUserByCode(String code) {
		User user = userDao.findUserByCode(code);
		return user;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDao.updateUser(user);
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}

}
