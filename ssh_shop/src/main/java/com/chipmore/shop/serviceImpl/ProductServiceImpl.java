package com.chipmore.shop.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chipmore.shop.dao.ProductDao;
import com.chipmore.shop.domain.Product;
import com.chipmore.shop.service.ProductService;
import com.chipmore.shop.utils.PageBean;

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

	@Override
	public PageBean<Product> findByPageCategory(Integer cid, int page) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);  //设置当前页数
		
		int limit = 8;
		pageBean.setLimit(limit);   //设置每页显示多少条记录
		
		int totalCount = 0;
		totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);  //设置总记录数
		
		int totalPage = 0;
		//totalPage = (int) Math.ceil(totalCount / limit);  //向上取整
		
		if(totalCount % limit == 0){
			totalPage = totalCount /limit;
		}else{
			totalPage = totalCount /limit + 1;
		}
		
		pageBean.setTotalPage(totalPage);  //设置总页数
		
		//从哪开始
		int begin = (page - 1) * limit;
		List<Product> productList =  productDao.findByPageCategory(cid,begin,limit);
		pageBean.setList(productList);
		return pageBean;
	}

	
	@Override
	public PageBean<Product> findByPageSecondCategory(Integer csid, int page) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);  //设置当前页数
		
		int limit = 8;
		pageBean.setLimit(limit);   //设置每页显示多少条记录
		
		int totalCount = 0;
		totalCount = productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);  //设置总记录数
		
		int totalPage = 0;
		//totalPage = (int) Math.ceil(totalCount / limit);  //向上取整
		
		if(totalCount % limit == 0){
			totalPage = totalCount /limit;
		}else{
			totalPage = totalCount /limit + 1;
		}
		
		pageBean.setTotalPage(totalPage);  //设置总页数
		
		//从哪开始
		int begin = (page - 1) * limit;
		List<Product> productList =  productDao.findByPageSecondCategory(csid,begin,limit);
		pageBean.setList(productList);
		return pageBean;
	}
	
	
	
	
	
}
