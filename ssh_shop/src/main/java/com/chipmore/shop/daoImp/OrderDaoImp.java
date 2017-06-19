package com.chipmore.shop.daoImp;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.chipmore.shop.dao.OrderDao;
import com.chipmore.shop.domain.Order;

@Repository("orderDao")
public class OrderDaoImp extends HibernateDaoSupport implements OrderDao {

	@Resource
	public void setSessionFactory0(SessionFactory sessionFactory){  
		super.setSessionFactory(sessionFactory);  
	}

	/**
	 * 保存订单数据到数据库
	 */
	@Override
	public void save(Order order) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(order);
	}
	
	
}
