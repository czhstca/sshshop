package com.chipmore.shop.service;

import java.util.List;

import com.chipmore.shop.domain.Category;

public interface CategoryService {
    
	/**
     *  查询所有一级分类的方法 
     * @return
     */
	List<Category> findAllCategory();

	
	
	
}
