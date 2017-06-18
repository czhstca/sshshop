package com.chipmore.shop.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.chipmore.shop.domain.Cart;
import com.chipmore.shop.domain.CartItem;
import com.chipmore.shop.domain.Product;
import com.chipmore.shop.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;

@Controller("cartAction")
public class CartAction extends ActionSupport {

	private Integer pid;  //商品id
	
	private Integer count;   //购买数量
	
	private ProductService productService;
	
	@Resource(name="productService")
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}



	public void setPid(Integer pid) {
		this.pid = pid;
	}



	public void setCount(Integer count) {
		this.count = count;
	}



	/**
	 * 添加购物车
	 */
	public String addCart(){
		//封装一个CartItem对象
		CartItem cartItem  = new CartItem();
		//设置数量和商品
		cartItem.setCount(count);
		Product buyProduct = productService.findByPid(pid);  //根据商品id查询商品信息
		cartItem.setProduct(buyProduct);
		
		//将购物项添加到购物车
		//购物车在session中，从session中获取购物车对象
		Cart cart = getCart();
		cart.addCartItem(cartItem);
		return "addCart";
	}

	
	/**
	 * 清空购物车
	 * @return
	 */
	public String clearCart(){
		Cart cart = getCart();
		cart.clearCart();  //清空操作
		return "clearCart";
	}

	/**
	 * 移除购物车中某个购物项
	 * @return
	 */
	public String removeItem(){
		Cart cart = getCart();
		cart.removeCartItem(pid);
		return "removeItem";
	}
	
	/**
	 * 我的购物车
	 * @return
	 */
	public String myCart(){
		//只需完成页面转向即可
		return "myCart";
	}
	
	
	
	/**
	 * 从session获得购物车的方法
	 * @return
	 */
	private Cart getCart() {
		// TODO Auto-generated method stub
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null){  //第一次访问session没有购物车对象，新建一个Cart对象后返回
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
}
