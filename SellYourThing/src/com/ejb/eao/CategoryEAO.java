package com.ejb.eao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.beans.CategoryBean;
import com.beans.SubcategoryBean;

@Stateless
@LocalBean
public class CategoryEAO {

    @PersistenceContext()
    EntityManager entityManager;
    private List<SubcategoryBean> sublist;

    public CategoryEAO() {
    }

    public List<CategoryBean> getCategories() {
        Query query = entityManager.createQuery("SELECT e FROM Category e");
        return query.getResultList();
    }

    public List<SubcategoryBean> getSubcategories() {

        return sublist;
    }

    public void setSubcategories(String catName) {
        if (catName != null) {
            Query query = entityManager.createQuery("SELECT e FROM Subcategory e WHERE e.category.name=:categoryName");
            query.setParameter("categoryName", catName);
            sublist = query.getResultList();
        } else {
            sublist = null;
        }
    }
}
