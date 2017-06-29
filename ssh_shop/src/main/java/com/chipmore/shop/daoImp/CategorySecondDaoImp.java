package com.chipmore.shop.daoImp;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.chipmore.shop.dao.CategorySecondDao;
import com.chipmore.shop.domain.CategorySecond;
import com.chipmore.shop.utils.PageHibernateCallback;

@Repository("categorySecondDao")
public class CategorySecondDaoImp extends HibernateDaoSupport implements CategorySecondDao  {

	@Resource
	public void setSessionFactory0(SessionFactory sessionFactory){  
		super.setSessionFactory(sessionFactory);  
	}

	/**
	 * 查询共有多少个二级分类
	 */
	@Override
	public int findCount() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from CategorySecond";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
		if(null != list && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 分页查询二级分类
	 */
	@Override
	public List<CategorySecond> findByPage(int begin, int limit) {
		// TODO Auto-generated method stub
		String hql = "from CategorySecond order by csid desc ";
		List<CategorySecond> list = this.getHibernateTemplate().execute(new PageHibernateCallback<CategorySecond>(hql, null, begin, limit));
		if(null != list && list.size() > 0){
			return list;
		}
		return null;
	}

	/**
	 * 保存二级分类的方法
	 */
	@Override
	public void save(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(categorySecond);
	}

	/**
	 * 通过二级分类id查找二级分类信息
	 */
	@Override
	public CategorySecond findByCsid(Integer csid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}

	/**
	 * 删除二级分类信息
	 */
	@Override
	public void delete(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(categorySecond);
	}

	/**
	 * 修改二级分类
	 */
	@Override
	public void update(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(categorySecond);
	}
	
	
	
	
}
