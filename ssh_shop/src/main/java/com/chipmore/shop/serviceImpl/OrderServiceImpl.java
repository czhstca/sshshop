package com.chipmore.shop.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chipmore.shop.dao.OrderDao;
import com.chipmore.shop.domain.Order;
import com.chipmore.shop.service.OrderService;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	private OrderDao orderDao;
	
	@Resource(name="orderDao")
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	public void save(Order order) {
		// TODO Auto-generated method stub
		orderDao.save(order);
	}
	
	
}
