package com.chipmore.shop.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.chipmore.shop.service.CategoryService;
import com.opensymphony.xwork2.ActionSupport;

@Controller("categoryAction")
public class CategoryAction extends ActionSupport {

	@Resource(name="categoryService")
	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	
	
}
