package com.ejb.eao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.beans.CategoryBean;

@Stateless
@LocalBean
public class CategoryEAO {
	@PersistenceContext()
    EntityManager entityManager;
	
	public CategoryEAO()
	{	
	}
	public List<CategoryBean> getCategories()
	{	
		
		Query query = entityManager.createQuery("SELECT e FROM Category e");
		
		return query.getResultList();
	}
}
