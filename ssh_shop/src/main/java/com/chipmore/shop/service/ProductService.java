package com.chipmore.shop.service;

import java.util.List;

import com.chipmore.shop.domain.Product;
import com.chipmore.shop.utils.PageBean;

public interface ProductService {

	List<Product> findHotProduct();

	List<Product> findNewProduct();

	Product findByPid(Integer pid);

	PageBean<Product> findByPageCategory(Integer cid, int page);

	PageBean<Product> findByPageSecondCategory(Integer csid, int page);

}
