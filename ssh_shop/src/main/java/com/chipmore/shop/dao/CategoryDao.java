package com.chipmore.shop.dao;

import java.util.List;

import com.chipmore.shop.domain.Category;

public interface CategoryDao {

	List<Category> findAllCategory();

	void save(Category category);

	Category findByCid(Integer cid);

	void delete(Category curcategory);

	void update(Category category);

}
