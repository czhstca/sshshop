package com.chipmore.shop.service;

import com.chipmore.shop.domain.CategorySecond;
import com.chipmore.shop.utils.PageBean;

public interface CategorySecondService {

	PageBean<CategorySecond> findByPage(Integer page);

	void save(CategorySecond categorySecond);

	CategorySecond findByCsid(Integer csid);

	void delete(CategorySecond categorySecond);

	void update(CategorySecond categorySecond);

}
