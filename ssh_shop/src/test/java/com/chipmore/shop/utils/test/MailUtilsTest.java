package com.chipmore.shop.utils.test;

import org.junit.Test;

import com.chipmore.shop.utils.MailUtils;

public class MailUtilsTest {

	@Test
	public void MailSendTest(){
		MailUtils.sendMail("aaa@shop.com", "11111111");
	}
}
