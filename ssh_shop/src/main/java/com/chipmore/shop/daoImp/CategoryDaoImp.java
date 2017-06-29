package com.chipmore.shop.daoImp;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.chipmore.shop.dao.CategoryDao;
import com.chipmore.shop.domain.Category;

@Repository("categoryDao")
public class CategoryDaoImp extends HibernateDaoSupport implements CategoryDao {

	@Resource
	public void setSessionFactory0(SessionFactory sessionFactory){  
		super.setSessionFactory(sessionFactory);  
	}

	/**
	 * 查询所有一级分类的方法
	 */
	@Override
	public List<Category> findAllCategory() {
		String hql = "from Category";
		List<Category> list = (List<Category>) this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	
	/**
	 * 保存一级分类的方法
	 */
	@Override
	public void save(Category category) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(category);
	}

	/**
	 * 根据id查找一级分类
	 */
	@Override
	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Category.class, cid);
	}

	/**
	 * 删除一级分类的方法
	 */
	@Override
	public void delete(Category curcategory) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(curcategory);
	}

	/**
	 * 修改一级分类的方法
	 */
	@Override
	public void update(Category category) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(category);
	}
	
	
	
	
	
}
