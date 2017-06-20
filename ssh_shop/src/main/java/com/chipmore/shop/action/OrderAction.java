package com.chipmore.shop.action;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.chipmore.shop.domain.Cart;
import com.chipmore.shop.domain.CartItem;
import com.chipmore.shop.domain.Order;
import com.chipmore.shop.domain.OrderItem;
import com.chipmore.shop.domain.User;
import com.chipmore.shop.service.OrderService;
import com.chipmore.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("orderAction")
public class OrderAction extends ActionSupport implements ModelDriven<Order> {

	private Order order = new Order();
	
	private OrderService orderService;
	
	private Integer page;  //页数
	
	@Resource(name="orderService")
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}


	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}
	
	
	public void setPage(Integer page) {
		this.page = page;
	}


	/**
	 * 生成订单的方法
	 * @return
	 */
	public String save(){
		//1.保存订单数据到数据库
		order.setOrdertime(new Date());
		order.setState(1);   //1.未付款    2.已付款,但没有发货   3.已经发货，但没有确认收货   4.确认收货
		Cart cart = (Cart)ServletActionContext.getRequest().getSession().getAttribute("cart");  //从session中获取购物车
		if(null == cart){
			this.addActionError("亲，您还没有购物，请先进行购物！");
			return "msg";
		}
		order.setTotal(cart.getTotal());   //总计金额，应该是购物车的总金额
		for(CartItem cartItem: cart.getCartItems()){  //获取每个订单项
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			
			order.getOrderItemSet().add(orderItem);
		}
		//获取订单所属用户
		User curUser = (User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(null == curUser){
			this.addActionError("亲，您还没有登录,请先登录!");
			return "login";
		}
		order.setUser(curUser);
		
		orderService.save(order);

		//2.将订单对象显示到页面上,直接将订单对象存入值栈，在页面中获取即可
		//清空购物车
		cart.clearCart();
		return "saveSuccess";
	}
	
	/**
	 * 查询我的订单
	 * @return
	 */
	public String findOrderByUid(){
		//获取用户id
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
		
		PageBean<Order> pageBean = orderService.findByPageUid(user.getUid(),page);
		//将分页数据放入值栈，最终显示到页面上
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findOrderByUidSuccess";
	}

	/**
	 * 根据订单id查找订单
	 * @return
	 */
	public String findByOid(){
		order = orderService.findByOid(order.getOid());
		return "findByOidSuccess";
	}
}
