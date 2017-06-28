package com.chipmore.shop.action;

import java.util.List;

import javax.annotation.Resource;

import com.chipmore.shop.domain.Category;
import com.chipmore.shop.service.CategoryService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台一级分类管理的action
 * @author SHAWN
 *
 */
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category> {

	private Category category = new Category();
	
	private CategoryService categoryService;
	
	
	@Resource(name="categoryService")
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}

	/**
	 * 查询所有一级分类
	 * @return
	 */
	public String findAll(){
		List<Category> cList = categoryService.findAllCategory();
		//将集合数据显示到页面上
		ActionContext.getContext().getValueStack().set("cList", cList);
		
		return "findAll";
	}
	
	/**
	 * 后台保存一级分类的方法
	 * @return
	 */
	public String save(){
		categoryService.save(category);
		
		return "saveSuccess";
	}
	
	/**
	 * 后台删除一级分类的方法
	 * @return
	 */
	public String delete(){
		//如果删除一级分类时也要删除二级分类，必须先根据id查询，再进行删除
		Category category1 = categoryService.findByCid(category.getCid());
		categoryService.delete(category1);
		return "deleteSuccess";
	}
	
}
