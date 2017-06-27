package com.chipmore.shop.daoImp;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.chipmore.shop.dao.OrderDao;
import com.chipmore.shop.domain.Order;
import com.chipmore.shop.utils.PageHibernateCallback;

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

	/**
	 * 根据用户id查找其拥有的订单数
	 */
	@Override
	public Integer findByCountUid(Integer uid) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql, uid);
		if(null != list && list.size() > 0){
			return list.get(0).intValue();
		}
		return null;
	}

	/**
	 * 根据用户id和当前页数查找详细的订单信息
	 */
	@Override
	public List<Order> findByPageUid(Integer uid, Integer begin, Integer limit) {
		// TODO Auto-generated method stub
		String hql = "from Order o where o.user.uid = ? order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, new Object[]{uid}, begin, limit));
		return list;
	}

	/**
	 * 根据订单id查找订单详细信息
	 */
	@Override
	public Order findByOid(Integer oid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Order.class, oid);
	}
	
	
	/**
	 * 修改订单的操作
	 */
	@Override
	public void updateOrder(Order currOrder) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(currOrder);
	}
	
	
}
