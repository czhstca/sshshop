package com.chipmore.shop.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.chipmore.shop.domain.Category;
import com.chipmore.shop.domain.Product;
import com.chipmore.shop.service.CategoryService;
import com.chipmore.shop.service.ProductService;
import com.chipmore.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

@Controller("productAction")
public class ProductAction extends ActionSupport implements ModelDriven<Product> {

	private ProductService productService;

	private Product product = new Product();
	
	private Integer cid;  //接收页面上传来的一级分类id
	
	private int page;  //接收页面传来的当前页数
	
	public void setPage(int page) {
		this.page = page;
	}

	private CategoryService categoryService;
	
	@Resource(name="categoryService")
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	
	@Resource(name="productService")
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getCid() {
		return cid;
	}

	/**
	 * 根据商品id查找商品详细信息
	 * @return
	 */
	public String findByPid(){
		
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}

	/**
	 * 根据一级分类id查找其下的所有二级分类
	 * @return
	 */
	public String findByCategory(){
		//先查询出所有的一级分类
		List<Category> categoryList = categoryService.findAllCategory();
		
		PageBean<Product> pageBean = productService.findByPageCategory(cid,page);  //根据一级分类查询商品，带分页
		
		ValueStack stack = ActionContext.getContext().getValueStack();
		stack.set("pageBean", pageBean);
		
		return "findByCid";
	}


	
}
