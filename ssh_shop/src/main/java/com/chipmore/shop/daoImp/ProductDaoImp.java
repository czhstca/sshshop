package com.chipmore.shop.daoImp;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.chipmore.shop.dao.ProductDao;
import com.chipmore.shop.domain.Product;

@Repository("productDao")
public class ProductDaoImp extends HibernateDaoSupport implements ProductDao {

	@Resource
	public void setSessionFactory0(SessionFactory sessionFactory){  
		super.setSessionFactory(sessionFactory);  
	}

	/**
	 * 首页上热门商品的查询
	 */
	@Override
	public List<Product> findHotProduct() {
		//带分页的查询有两种方式完成
		//1.使用离线查询
		//2.hibernate方法的execute（）方法，实现callback接口
		
		//以下为离线查询实现的分页
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//查询热门商品，is_hot=1
		criteria.add(Restrictions.eq("is_hot", 1));
		//倒序输出，显示最新商品(按商品上传日期倒序排序)
		criteria.addOrder(Order.desc("pdate"));
		//执行查询
		List<Product> hotProductList =  (List<Product>) this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		if(hotProductList != null && hotProductList.size() > 0){
			return hotProductList;
		}
		return null;
	}

	/**
	 * 查询首页上最新商品
	 */
	@Override
	public List<Product> findNewProduct() {

		//以下为离线查询实现的分页
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//倒序输出，显示最新商品(按商品上传日期倒序排序)
		criteria.addOrder(Order.desc("pdate"));
		//执行查询
		List<Product> hotProductList =  (List<Product>) this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		if(hotProductList != null && hotProductList.size() > 0){
			return hotProductList;
		}
		return null;
	}

	
	/**
	 * 根据商品id查找商品详细信息
	 */
	@Override
	public Product findBypid(Integer pid) {
		Product product = this.getHibernateTemplate().get(Product.class, pid);
		return product;
	}
	
	
	
}
