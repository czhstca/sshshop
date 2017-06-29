package com.chipmore.shop.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.chipmore.shop.domain.Category;
import com.chipmore.shop.domain.CategorySecond;
import com.chipmore.shop.service.CategorySecondService;
import com.chipmore.shop.service.CategoryService;
import com.chipmore.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("categorySecondAction")
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {

	private CategorySecond categorySecond = new CategorySecond();
	
	private CategorySecondService categorySecondService;
	
	private Integer page;  //接收page参数
	
	
	private CategoryService categoryService;  //一级分类service
	
	@Resource(name="categorySecondService")
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	@Override
	public CategorySecond getModel() {
		// TODO Auto-generated method stub
		return categorySecond;
	}
	
	
	public void setPage(Integer page) {
		this.page = page;
	}

	@Resource(name="categoryService")
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * 分页查询二级分类的方法
	 * @return
	 */
	public String findAllByPage(){
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		//将pageBean保存到值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findAll";
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String addpage(){
		//查询所有一级分类
		List<Category> cList = categoryService.findAllCategory();
		//把数据显示到页面中下拉列表中
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPageSuccess";
	}
	
	/**
	 * 二级分类的添加方法
	 * @return
	 */
	public String save(){
		categorySecondService.save(categorySecond);
		
		return "saveSuccess";
	}
	
	/**
	 * 删除二级分类的方法
	 * @return
	 */
	public String delete(){
		//如果要级联删除一级分类，必须先查询再删除,配置cascade
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		
		return "deleteSuccess";
	}

	/**
	 * 编辑二级分类的方法
	 * @return
	 */
	public String edit(){
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		
		List<Category> cList = categoryService.findAllCategory();
		
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	
	/**
	 * 修改二级分类的方法
	 * @return
	 */
	public String update(){
		
		categorySecondService.update(categorySecond);
		
		return "updateSuccess";
	}
}
