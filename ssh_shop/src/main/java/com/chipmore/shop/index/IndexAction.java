package com.chipmore.shop.index;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.chipmore.shop.domain.Category;
import com.chipmore.shop.domain.Product;
import com.chipmore.shop.service.CategoryService;
import com.chipmore.shop.service.ProductService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 访问首页的action
 * @author SHAWN
 *
 */
@Component
public class IndexAction extends ActionSupport{

	private CategoryService categoryService;
	
	private ProductService productService;
	
	@Resource(name="categoryService")
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	
	@Resource(name="productService")
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}



	/**
	 * 默认执行该方法
	 */
	public String execute(){
		
		//添加查询所有一级首页的方法
		List<Category> categoryList = categoryService.findAllCategory();
		//将一级分类存入session范围
		ActionContext.getContext().getSession().put("categoryList", categoryList);
		
		//查询热门商品
		List<Product> hotProductList = productService.findHotProduct();
		//将数据保存到值栈中
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.set("hotProductList", hotProductList);
		
		//查询最新商品
		List<Product> latestProductList = productService.findNewProduct();
		//将数据保存到值栈中
		valueStack.set("latestProductList", latestProductList);
		return "index";
	}
}
