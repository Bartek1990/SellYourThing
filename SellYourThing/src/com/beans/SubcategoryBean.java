package com.beans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class SubcategoryBean {
	
	private String subName;

	public String getSubName() {
		System.out.println("subName");
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}
}
