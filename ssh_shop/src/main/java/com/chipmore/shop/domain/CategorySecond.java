package com.chipmore.shop.domain;

import java.util.HashSet;
import java.util.Set;

public class CategorySecond {

	private Integer csid;
	
	private String csname;
	
	//一个二级分类只能从属于一个一级分类
	private Category category; 
	
	//一个二级分类下有多个产品
	private Set<Product> productList = new HashSet<Product>();

	public Integer getCsid() {
		return csid;
	}

	public Set<Product> getProductList() {
		return productList;
	}

	public void setProductList(Set<Product> productList) {
		this.productList = productList;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public String getCsname() {
		return csname;
	}

	public void setCsname(String csname) {
		this.csname = csname;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
