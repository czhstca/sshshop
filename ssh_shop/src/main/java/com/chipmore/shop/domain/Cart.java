package com.chipmore.shop.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart implements Serializable{

	private Map<Integer,CartItem> cartItemMap = new LinkedHashMap<Integer,CartItem>();  //购物项集合(有序)
	
	private double total;  //总计
	
	//表示页面中有一个cartItems的属性，在页面上进行遍历非常简单
	public Collection<CartItem> getCartItems(){
		return cartItemMap.values();
	}
	
	//购物车拥有的功能：
	//1.将购物项添加到购物车
	public void addCartItem(CartItem cartItem){
		//添加时，需要先判断购物车中是否已经存在该购物项
		//如果存在，则要更新该购物项对应的数量和总计，总计+= 该购物项小计
		//如果不存在，新建一个购物项，并添加到map中，总计+= 该购物项小计
		
		Integer pid = cartItem.getProduct().getPid();
		if(cartItemMap.containsKey(pid)){  //判断购物车中是否包含该新增的购物项
			//存在
			CartItem oldCartItem = cartItemMap.get(pid);  //获取购物车中该新增商品对应的原购物项
			oldCartItem.setCount(oldCartItem.getCount() + cartItem.getCount());  //加上数量
			
		}else{
			//不存在
			cartItemMap.put(pid, cartItem); 
		}
		total += cartItem.getSubtotal();  //加上小计
	}
	
	//2.删除购物车中某个购物项
	public void removeCartItem(Integer pid){
		//购物车中移除某个购物项
		//总计 = 总计 - 移除购物项的小计
		CartItem deletedCartItem = cartItemMap.remove(pid);
		total -= deletedCartItem.getSubtotal();
	}
	
	//3.清空购物车
	public void clearCart(){
		//将所有购物项清空，总计设置为0
		cartItemMap.clear();
		total = 0;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
	
}
