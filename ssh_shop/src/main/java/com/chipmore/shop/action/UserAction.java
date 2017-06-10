package com.chipmore.shop.action;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
	private String checkcode;  //验证码
	
	public void setCheckCode(String checkcode) {
		this.checkcode = checkcode;
	}
	
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
		//0.先判断验证码
		//1.先将user对象存入数据库
		//2.发送激活邮件
		
		//从session获取验证码的正确的值
		String checkCode1 = ServletActionContext.getRequest().getSession().getAttribute("checkcode").toString();
		if(!checkCode1.equalsIgnoreCase(checkcode)){
			this.addActionError("您的验证码输入错误!");
			return "checkCodeFail";
		}
		
		userService.saveUser(user);
		this.addActionMessage("注册成功，请去邮箱激活!");
		return "msg";
	}
	
	
	/**
	 * 用户激活的方法
	 * @return
	 */
	public String active(){
		
		//1.先根据用户点击传递过来的激活码查询用户是否存在
		String code = user.getCode();
		User myUser = userService.findUserByCode(code);
		
		
		//2.若存在，则更改用户状态为1--已激活，否则激活码被用户篡改了
		if(myUser == null){
			//激活失败
			this.addActionMessage("激活失败,因为激活码不正确!");
			
		}else{
			//激活成功,修改用户的状态为已激活
			myUser.setState(1);
			myUser.setCode(null);
			userService.updateUser(myUser);  //更新用户状态
			//这里也可以直接将用户信息存入session中自动跳转至首页!
			this.addActionMessage("激活成功!");
		}
		return "msg";
	}
	
	/**
	 * 跳转到登录页面的方法
	 * @return
	 */
	public String loginPage(){
		return "loginPage";
	}
	
	/**
	 * 用户登录的方法
	 * @return
	 */
	public String login(){
		
		//根据用户名、密码、状态（为1）才能登录成功
		User existUser = userService.login(user);
		if(existUser == null){
			//登录失败
			this.addActionError("登录失败,用户名或密码错误或用户还未激活!");
			return LOGIN;
		}else{
			//登录成功
			//将用户信息存入session中，再跳转到首页
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.setAttribute("existUser", existUser);
			return "loginSuccess";
		}
	}
	
	/**
	 * 用户退出的方法
	 * @return
	 */
	public String quit(){
		//销毁session
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.invalidate();
		//跳转页面
		return "quit";
	}
	
	
	@Override
	public User getModel() {
		return user;
	}
}
