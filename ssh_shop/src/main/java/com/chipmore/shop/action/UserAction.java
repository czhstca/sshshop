package com.chipmore.shop.action;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.chipmore.shop.domain.User;
import com.chipmore.shop.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户模块的action
 * @author SHAWN
 *
 */
@Controller("userAction")
public class UserAction extends ActionSupport implements ModelDriven<User> {

	private User user = new User();
	
	private UserService userService;
	
	
	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 跳转到注册页面的执行方法
	 * @return
	 */
	public String registPage(){
		
		return "registPage";
	}
	
	/**
	 * 根据用户名查找用户
	 * @return
	 */
	public String findByName() throws IOException{
		
		//调用service查询
		User existUser = userService.findByUsername(user.getUsername());
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		
		if(existUser != null){
			//查询到用户，用户已存在
			response.getWriter().println("<font color='red'>用户名已存在</font>");
		}else{
			//未查询到用户，用户名可以使用!
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		
		return NONE;
	}

	
	/**
	 * 用户注册的方法
	 * @return
	 */
	public String regist(){
		
		//将数据插入数据库前应该先对表单提交的数据进行数据校验
		//1.先将user对象存入数据库
		//2.发送激活邮件
		userService.saveUser(user);
		
		
		
		
		return NONE;
	}
	
	
	
	@Override
	public User getModel() {
		return user;
	}
}
