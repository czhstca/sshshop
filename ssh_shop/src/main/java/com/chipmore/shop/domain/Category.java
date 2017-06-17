package com.chipmore.shop.domain;

import java.util.HashSet;
import java.util.Set;

public class Category {

	private Integer cid;
	
	private String cname;
	
	//一个一级分类对应多个二级分类
	private Set<CategorySecond> categorySecondList = new HashSet<CategorySecond>();

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Set<CategorySecond> getCategorySecondList() {
		return categorySecondList;
	}

	public void setCategorySecondList(Set<CategorySecond> categorySecondList) {
		this.categorySecondList = categorySecondList;
	}
	
	
}
