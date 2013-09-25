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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((subName == null) ? 0 : subName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		System.out.println("Waliduje w subcategorybean");
		return true;
	}
}
