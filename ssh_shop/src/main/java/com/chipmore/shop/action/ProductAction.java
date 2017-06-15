package com.chipmore.shop.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.chipmore.shop.domain.Product;
import com.chipmore.shop.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("productAction")
public class ProductAction extends ActionSupport implements ModelDriven<Product> {

	private ProductService productService;

	private Product product = new Product();
	
	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	
	@Resource(name="productService")
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	/**
	 * 根据商品id查找商品详细信息
	 * @return
	 */
	public String findByPid(){
		
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}



	
}
