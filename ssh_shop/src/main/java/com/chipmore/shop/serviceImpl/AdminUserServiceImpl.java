package com.chipmore.shop.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chipmore.shop.dao.AdminUserDao;
import com.chipmore.shop.domain.AdminUser;
import com.chipmore.shop.service.AdminUserService;

@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService {

	private AdminUserDao adminUserDao;

	@Resource(name="adminUserDao")
	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	/**
	 * 后台管理员登录的方法
	 */
	@Override
	public AdminUser login(AdminUser adminUser) {
		// TODO Auto-generated method stub
		return adminUserDao.login(adminUser);
	}
	
	
	
}
