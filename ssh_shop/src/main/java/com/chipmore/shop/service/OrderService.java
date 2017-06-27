package com.chipmore.shop.service;

import com.chipmore.shop.domain.Order;
import com.chipmore.shop.utils.PageBean;

public interface OrderService {

	void save(Order order);

	PageBean<Order> findByPageUid(Integer uid, Integer page);

	Order findByOid(Integer oid);

	void updateOrder(Order currOrder);

	
}
