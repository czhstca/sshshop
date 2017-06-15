package com.chipmore.shop.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chipmore.shop.dao.ProductDao;
import com.chipmore.shop.domain.Product;
import com.chipmore.shop.service.ProductService;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService{

	private ProductDao productDao;

	@Resource(name="productDao")
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public List<Product> findHotProduct() {
		// TODO Auto-generated method stub
		return productDao.findHotProduct();
	}

	@Override
	public List<Product> findNewProduct() {
		// TODO Auto-generated method stub
		return productDao.findNewProduct();
	}

	@Override
	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return productDao.findBypid(pid);
	}
	
	
	
	
	
}
