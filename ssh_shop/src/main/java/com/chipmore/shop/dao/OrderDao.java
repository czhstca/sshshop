package com.chipmore.shop.dao;

import java.util.List;

import com.chipmore.shop.domain.Order;

public interface OrderDao {

	void save(Order order);

	Integer findByCountUid(Integer uid);

	List<Order> findByPageUid(Integer uid, Integer begin, Integer limit);

	Order findByOid(Integer oid);
	
}
