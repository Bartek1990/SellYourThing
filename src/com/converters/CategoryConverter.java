package com.converters;

import com.ejb.eao.CategoryEAO;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Category;

@ManagedBean(name = "categoryConverterBean")
@FacesConverter(value = "categoryConverter")
@ViewScoped
public class CategoryConverter implements Converter {

    @PersistenceContext()
    private transient EntityManager em;
    String categoryName = null;
    private boolean rendered;

    public boolean isRendered() {
        return rendered;
    }

    public void setRendered(boolean rendered) {
        this.rendered = rendered;
    }
    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
        // TODO Auto-generated method stub
        Query query = em.createQuery("SELECT c FROM Category c WHERE c.name=?1");
        query.setParameter("1", value);
        
        if (!value.equals("Wybierz Kategorię")) {
            System.out.println(value);
            rendered = true;
            categoryName = value;
            return query.getSingleResult();
        } else {
            rendered = false;
            categoryName = null;
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
        // TODO Auto-generated method stub
             if(((String) ((Category) value).getName()) != null)   
                 return (String) ((Category) value).getName();
             else return "";
    }
    @EJB
    CategoryEAO service;

    public void loadSubcategories() {
        service.setSubcategories(categoryName);
    }
}
