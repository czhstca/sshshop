package com.chipmore.shop.daoImp;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.chipmore.shop.dao.AdminUserDao;
import com.chipmore.shop.domain.AdminUser;

@Repository("adminUserDao")
public class AdminUserDaoImpl extends HibernateDaoSupport implements AdminUserDao{

	@Resource
	public void setSessionFactory0(SessionFactory sessionFactory){  
		super.setSessionFactory(sessionFactory);  
	}

	/**
	 * 后台管理员登录的方法
	 */
	@Override
	public AdminUser login(AdminUser adminUser) {
		
		String sql = "from AdminUser where username=? and password=? ";
		List<AdminUser> list  = (List<AdminUser>)this.getHibernateTemplate().find(sql, adminUser.getUsername(),adminUser.getPassword());
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	
	
}
