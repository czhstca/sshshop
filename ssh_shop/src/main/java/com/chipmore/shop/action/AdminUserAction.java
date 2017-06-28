package com.chipmore.shop.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.chipmore.shop.domain.AdminUser;
import com.chipmore.shop.service.AdminUserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台管理员的action
 * @author SHAWN
 *
 */
@Controller("adminUserAction")
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser> {

	private AdminUser adminUser = new AdminUser();
	
	private AdminUserService adminUserService;
	
	@Resource(name="adminUserService")
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

	
	@Override
	public AdminUser getModel() {
		// TODO Auto-generated method stub
		return adminUser;
	}

	/**
	 * 后台管理员登录的方法
	 * @return
	 */
	public String login(){
		AdminUser existUser = adminUserService.login(adminUser);
		if(existUser == null){
			//登录失败
			this.addActionError("亲，您的用户名或密码错误!");
			return "loginFail";
		}else{
			//登录成功
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existUser);
			return "loginSuccess";
		}
	}
	
	
	
	
	
}
