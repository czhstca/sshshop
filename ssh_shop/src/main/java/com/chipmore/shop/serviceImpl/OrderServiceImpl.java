package com.chipmore.shop.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chipmore.shop.dao.OrderDao;
import com.chipmore.shop.domain.Order;
import com.chipmore.shop.service.OrderService;
import com.chipmore.shop.utils.PageBean;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	private OrderDao orderDao;
	
	@Resource(name="orderDao")
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	/**
	 * 保存订单的方法
	 */
	@Override
	public void save(Order order) {
		// TODO Auto-generated method stub
		orderDao.save(order);
	}

	/**
	 * 获取我的订单信息
	 */
	@Override
	public PageBean<Order> findByPageUid(Integer uid, Integer page) {
		// TODO Auto-generated method stub
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setPage(page);
		Integer limit =  3;
		pageBean.setLimit(limit);
		
		Integer totalCount = null;
		totalCount = orderDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		
		Integer totalPage = null;
		if(totalCount % limit == 0){
			
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		Integer begin = (page - 1) * limit;
		List<Order> list = orderDao.findByPageUid(uid,begin,limit);
		pageBean.setList(list);
		
		return pageBean;
	}

	/**
	 * 根据订单编号查询订单信息
	 */
	@Override
	public Order findByOid(Integer oid) {
		// TODO Auto-generated method stub
		return orderDao.findByOid(oid);
	}

	/**
	 * 修改订单
	 */
	@Override
	public void updateOrder(Order currOrder) {
		// TODO Auto-generated method stub
		orderDao.updateOrder(currOrder);
	}
	
	
}
