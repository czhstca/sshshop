package com.chipmore.shop.dao;

import java.util.List;

import com.chipmore.shop.domain.CategorySecond;

public interface CategorySecondDao {

	int findCount();

	List<CategorySecond> findByPage(int begin, int limit);

	void save(CategorySecond categorySecond);

	CategorySecond findByCsid(Integer csid);

	void delete(CategorySecond categorySecond);

	void update(CategorySecond categorySecond);

}
