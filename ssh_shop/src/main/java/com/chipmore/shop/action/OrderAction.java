package com.chipmore.shop.action;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.chipmore.shop.domain.Cart;
import com.chipmore.shop.domain.CartItem;
import com.chipmore.shop.domain.Order;
import com.chipmore.shop.domain.OrderItem;
import com.chipmore.shop.domain.User;
import com.chipmore.shop.service.OrderService;
import com.chipmore.shop.utils.PageBean;
import com.chipmore.shop.utils.PaymentUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("orderAction")
public class OrderAction extends ActionSupport implements ModelDriven<Order> {

	private Order order = new Order();
	
	private OrderService orderService;
	
	private Integer page;  //页数
	
	private String pd_FrpId;  //接收支付通道编码
	
	//付款成功后响应的数据
	private String r6_Order;
	
	private String r3_Amt;
	
	
	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}


	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}


	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}


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
	
	
	/**
	 * 为订单付款
	 * @return
	 * @throws IOException 
	 */
	public String payOrder() throws IOException{
		//修改订单
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		orderService.updateOrder(currOrder);
		
		//为订单付款
		String p0_Cmd = "Buy";  //业务类型
		String p1_MerId = "10001126856";  //商户编号
		String p2_Order = order.getOid().toString();  //订单编号
		String p3_Amt = "0.01";  //付款金额
		String p4_Cur = "CNY";  //交易币种
		String p5_Pid = "";  //商品名称
		String p6_Pcat = "";  //商品种类
		String p7_Pdesc = "";  //商品描述
		String p8_Url = "http://localhost:8080/shop/order_callBack.action";  //支付成功后跳转的页面路径
		String p9_SAF = "";  //送货地址
		String pa_MP = "";  //商户扩展信息
		String pd_FrpId = this.pd_FrpId;  //支付通道编码
		String pr_NeedResponse = "1";  //应答机制
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);  //数字签名
		
		//向易宝传参数
		StringBuffer buffer = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		buffer.append("p0_Cmd=").append(p0_Cmd).append("&");
		buffer.append("p1_MerId=").append(p1_MerId).append("&");
		buffer.append("p2_Order=").append(p2_Order).append("&");
		buffer.append("p3_Amt=").append(p3_Amt).append("&");
		buffer.append("p4_Cur=").append(p4_Cur).append("&");
		buffer.append("p5_Pid=").append(p5_Pid).append("&");
		buffer.append("p6_Pcat=").append(p6_Pcat).append("&");
		buffer.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		buffer.append("p8_Url=").append(p8_Url).append("&");
		buffer.append("p9_SAF=").append(p9_SAF).append("&");
		buffer.append("pa_MP=").append(pa_MP).append("&");
		buffer.append("pd_FrpId=").append(pd_FrpId).append("&");
		buffer.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		buffer.append("hmac=").append(hmac);
		
		//重定向到易宝
		ServletActionContext.getResponse().sendRedirect(buffer.toString());
		
		return NONE;
	}
	
	/**
	 * 付款成功后的转向动作
	 * @return
	 */
	public String callBack(){
		//修改订单状态 -> 已经付款
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		currOrder.setState(2);
		orderService.updateOrder(currOrder);
		
		//在页面上显示付款成功的信息
		this.addActionMessage("订单支付成功! 订单编号:" +r6_Order + ", 金额:" + r3_Amt );
		
		return "msg";
	}
	
}
