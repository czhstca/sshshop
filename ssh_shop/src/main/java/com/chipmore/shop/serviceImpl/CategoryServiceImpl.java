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
	
	/**
	 * 保存一级分类的方法
	 */
	@Override
	public void save(Category category) {
		// TODO Auto-generated method stub
		categoryDao.save(category);
	}

	/**
	 * 根据id查找一级分类
	 */
	@Override
	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return categoryDao.findByCid(cid);
	}

	/**
	 * 删除一级分类
	 */
	@Override
	public void delete(Category curcategory) {
		// TODO Auto-generated method stub
		categoryDao.delete(curcategory);
	}

	/**
	 * 修改一级分类
	 */
	@Override
	public void update(Category category) {
		// TODO Auto-generated method stub
		categoryDao.update(category);
	}
	
	
	
	
}
