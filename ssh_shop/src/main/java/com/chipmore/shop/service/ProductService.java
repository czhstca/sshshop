package com.chipmore.shop.service;

import java.util.List;

import com.chipmore.shop.domain.Product;

public interface ProductService {

	List<Product> findHotProduct();

	List<Product> findNewProduct();

	Product findByPid(Integer pid);

}
