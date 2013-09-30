package com.beans;

import com.ejb.eao.CategoryEAO;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class CategoryBean {

    private String name;
    private List<SubcategoryBean> subcategories;
    @EJB
    CategoryEAO service;

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

    public String addCategory() {
        if (!service.persistCategory(this)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Kategoria " + name + " już istnieje", null));
            return "failure";
        }
        return "success";
    }

    public String deleteCategory() {
        if (!service.deleteCategory(this)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Kategoria " + name + " nie istnieje", null));
            return "failure";
        }
        return "success";
    }
}
