package com.converters;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Subcategory;

@ManagedBean(name = "subcategoryConverterBean") 
@FacesConverter(value = "subcategoryConverter")
public class SubcategoryConverter implements Converter {

	@PersistenceContext()
	private transient EntityManager em;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("SELECT c FROM Subcategory c WHERE c.subName=?1");
        query.setParameter("1", value);
		return query.getSingleResult();
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		// TODO Auto-generated method stub
		return ((String) ((Subcategory) value).getSubName());
	}

}
