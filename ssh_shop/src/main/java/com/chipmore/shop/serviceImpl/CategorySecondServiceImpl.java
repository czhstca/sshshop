package com.chipmore.shop.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chipmore.shop.dao.CategorySecondDao;
import com.chipmore.shop.domain.CategorySecond;
import com.chipmore.shop.service.CategorySecondService;
import com.chipmore.shop.utils.PageBean;

@Service("categorySecondService")
@Transactional
public class CategorySecondServiceImpl implements CategorySecondService{

	private CategorySecondDao categorySecondDao;

	@Resource(name="categorySecondDao")
	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	/**
	 * 分页查询二级分类的方法
	 */
	@Override
	public PageBean<CategorySecond> findByPage(Integer page) {
		// TODO Auto-generated method stub
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		pageBean.setPage(page);
		int limit = 10;
		pageBean.setLimit(limit);
		int totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (page - 1) * limit;
		List<CategorySecond> list = categorySecondDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean ;
	}

	/**
	 * 后台添加二级分类的方法
	 */
	@Override
	public void save(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.save(categorySecond);
	}

	/**
	 * 通过二级分类id查找二级分类
	 */
	@Override
	public CategorySecond findByCsid(Integer csid) {
		// TODO Auto-generated method stub
		return categorySecondDao.findByCsid(csid);
	}

	/**
	 * 删除二级分类信息
	 */
	@Override
	public void delete(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.delete(categorySecond);
	}

	/**
	 * 修改二级分类信息
	 */
	@Override
	public void update(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.update(categorySecond);
	}
	
	
}
