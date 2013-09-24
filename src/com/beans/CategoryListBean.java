package com.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.ejb.eao.CategoryEAO;

import model.Category;

@ManagedBean
public class CategoryListBean {

    private List<CategoryBean> category;
    private List<SubcategoryBean> subcategory;

    @EJB
    CategoryEAO service;

    public List<CategoryBean> getCategory() {
        category = service.getCategories();
        return category;
    }

    public void setCategory(List<CategoryBean> category) {
        this.category = category;
    }

    public List<SubcategoryBean> getSubcategory() {
        subcategory = service.getSubcategories();
        return subcategory;
    }

    public void setSubcategory(List<SubcategoryBean> subcategory) {
        this.subcategory = subcategory;
    }
}
