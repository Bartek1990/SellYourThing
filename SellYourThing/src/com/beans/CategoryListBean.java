package com.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ejb.eao.CategoryEAO;

import model.Category;

@ManagedBean
public class CategoryListBean {
	
	private List<CategoryBean> category;
	
	@EJB
	CategoryEAO service;
	
	public List<CategoryBean> getCategory() {
		category = null;
		category = service.getCategories();
		return category;
		
	}
	public void setCategory(List<CategoryBean> category) {
		this.category = category;
	}
	
}
