package com.bitacademy.jblog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.jblog.repository.CategoryDao;
import com.bitacademy.jblog.repository.CategoryVo;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;
	
	@Override
	public List<CategoryVo> getCategoryList(Long userNo) {
		List<CategoryVo> categoryList = categoryDao.selectAllCategory(userNo);
		return categoryList;
	}

	@Override
	public boolean insertCategory(CategoryVo cvo) {
		int insertedCategoryCount = categoryDao.insertCategory(cvo);
		return 1 == insertedCategoryCount;
	}
	
	@Override
	public List<CategoryVo> getPostNum() {
		List<CategoryVo> numList = categoryDao.getNumOfPost();
		return numList;
	}

}
