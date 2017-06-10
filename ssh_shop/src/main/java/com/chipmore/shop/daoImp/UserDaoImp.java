package com.chipmore.shop.daoImp;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.chipmore.shop.dao.UserDao;
import com.chipmore.shop.domain.User;

@Repository("userDao")
public class UserDaoImp extends HibernateDaoSupport implements UserDao {

	@Resource
	public void setSessionFactory0(SessionFactory sessionFactory){  
		super.setSessionFactory(sessionFactory);  
	}
	
	
	@Override
	public User findByUsername(String username) {
		String hql = "from User where username=?";
		List<User> list =  (List<User>) this.getHibernateTemplate().find(hql, username);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}


	@Override
	public void saveUser(User user) {
		this.getHibernateTemplate().save(user);
	}


	@Override
	public User findUserByCode(String code) {
		String hql = "from User where code=?";
		List<User> list  = (List<User>)this.getHibernateTemplate().find(hql, code);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}


	@Override
	public void updateUser(User user) {
		//修改用户状态
		this.getHibernateTemplate().update(user);
	}


	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		String sql = "from User where username=? and password=? and state=?";
		List<User> list  = (List<User>)this.getHibernateTemplate().find(sql, user.getUsername(),user.getPassword(),1);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}
