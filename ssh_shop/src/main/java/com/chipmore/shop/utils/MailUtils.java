package com.chipmore.shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * 发送邮件的工具类
 * @author SHAWN
 *
 */
public class MailUtils {

	/**
	 * 发送邮件的方法
	 * @param to 收件人
	 * @param code 激活码
	 */
	public static void sendMail(String to,String code){
		//1.获得一个session对象
		//prop:  参数
		//authenticator:  认证
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		Session session = Session.getInstance(props, new Authenticator() {
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// 第一个参数：发送邮件服务器名称
				// 第二个参数：密码
				return new PasswordAuthentication("service@shop.com", "123");
			}
		});
		
		
		//2.创建一个代表邮件的Message对象
		Message message = new MimeMessage(session);
		//设置发件人
		try {
			message.setFrom(new InternetAddress("service@shop.com"));
			// 第一个参数：设置类型为收件人
			// 第二个参数：收件人对象
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			//CC为抄送
			//message.addRecipient(RecipientType.CC, new InternetAddress(cc));
			//设置主题
			message.setSubject("来自购物天堂梵门桥弄的激活邮件");
			//设置正文
			//第一个参数：正文
			//第二个参数：格式
			message.setContent("<h1>请点击下面的链接完成激活操作!</h1><h3><a href='http://192.168.1.2:8080/ssh_shop/user_active.action?code="+code+"'>http://192.168.1.2:8080/ssh_shop/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
			
			
			//3.发送邮件
			Transport.send(message);
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
