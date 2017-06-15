package com.chipmore.shop.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chipmore.shop.dao.CategoryDao;
import com.chipmore.shop.domain.Category;
import com.chipmore.shop.service.CategoryService;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Resource(name="categoryDao")
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public List<Category> findAllCategory() {
		return categoryDao.findAllCategory();
	}
	
	
	
	
}
