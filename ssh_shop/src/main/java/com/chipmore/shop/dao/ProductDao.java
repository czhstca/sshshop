package com.chipmore.shop.dao;

import java.util.List;

import com.chipmore.shop.domain.Product;

public interface ProductDao {

	List<Product> findHotProduct();

	List<Product> findNewProduct();

	Product findBypid(Integer pid);

	int findCountCid(Integer cid);

	List<Product> findByPageCategory(Integer cid, int begin, int limit);

	List<Product> findByPageSecondCategory(Integer csid, int begin, int limit);

	int findCountCsid(Integer csid);

}
