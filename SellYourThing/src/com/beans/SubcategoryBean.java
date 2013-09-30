package com.beans;

import com.ejb.eao.CategoryEAO;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.Category;

@ManagedBean
@RequestScoped
public class SubcategoryBean {

    @EJB
    CategoryEAO service;
    private String subName;
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String addSubcategory() {
        if (!service.persistSubCategory(this)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Subkategoria " + category.getName() + " > " + subName + " już istnieje", null));
            return "failure";
        }
        return "success";
    }

    public String deleteSubcategory() {
        if (!service.deleteSubCategory(this)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Subkategoria " + category.getName() + " > " + subName + " nie istnieje", null));
            return "failure";
        }
        return "success";
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
