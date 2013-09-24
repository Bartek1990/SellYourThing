package com.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class CategoryBean {
	
	private String name;
	private List<SubcategoryBean> subcategories;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<SubcategoryBean> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<SubcategoryBean> subcategories) {
		this.subcategories = subcategories;
	}

	
}
