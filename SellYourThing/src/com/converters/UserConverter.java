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
import model.User;

@ManagedBean(name = "userConverterBean")
@FacesConverter(value = "userConverter")
@ViewScoped
public class UserConverter implements Converter {

    @PersistenceContext()
    private transient EntityManager em;

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
        // TODO Auto-generated method stub
        Query query = em.createQuery("SELECT c FROM User c WHERE c.email=:email");
        System.out.println("wartosc: " + value);
        query.setParameter("email", value);
        User user = (User) query.getSingleResult();
        System.out.println("zwracam: " + user.getEmail());
        return query.getSingleResult();
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
        // TODO Auto-generated method stub
        if (((String) ((User) value).getEmail()) != null) {
            return (String) ((User) value).getEmail();
        } else {
            return "";
        }
    }
}
