package com.chipmore.shop.index;

import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 访问首页的action
 * @author SHAWN
 *
 */
@Component
public class IndexAction extends ActionSupport{

	/**
	 * 默认执行该方法
	 */
	public String execute(){
		
		
		return "index";
	}
}
