package com.chipmore.shop.dao;

import java.util.List;

import com.chipmore.shop.domain.Product;

public interface ProductDao {

	List<Product> findHotProduct();

	List<Product> findNewProduct();

	Product findBypid(Integer pid);

}
