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
import com.chipmore.shop.utils.PageHibernateCallback;

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

	/**
	 * 根据一级分类id查找其下对应商品数量
	 */
	@Override
	public int findCountCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ? ";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql, cid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 根据一级分类id查找其下所有商品详细信息
	 */
	@Override
	public List<Product> findByPageCategory(Integer cid, int begin, int limit) {
		//三表联查的hql写法!!
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		//分页除了离线查询的另一种写法
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	/**
	 * 根据二级分类id查找其下商品数量
	 */
	@Override
	public int findCountCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid = ? ";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql, csid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	
	/**
	 * 根据二级分类id查找其下所有商品详细信息
	 */
	@Override
	public List<Product> findByPageSecondCategory(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		
		//分页除了离线查询的另一种写法
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}


	
	
	
	
}
