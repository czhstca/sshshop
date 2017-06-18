package com.chipmore.shop.domain;

/**
 * 购物项实体类
 * @author SHAWN
 *
 */
public class CartItem {

	private Product product;  //商品
	
	private int count;   //数量
	
	private double subtotal;   //小计

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	//小计自动计算得到，隐藏其set方法
	public double getSubtotal() {
		return count * product.getShop_price();
	}

/*	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}*/
	
	
}
