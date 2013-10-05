package com.ejb.eao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.beans.CategoryBean;
import com.beans.SubcategoryBean;
import model.Category;
import model.Subcategory;

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
        query.setHint("javax.persistence.cache.storeMode", "REFRESH");
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

    public String persistSubCategory(SubcategoryBean subcategoryBean) {
        Query query = entityManager.createQuery("SELECT e FROM Subcategory e WHERE e.subName =:subName AND e.category.catId =:categoryId");
        query.setParameter("subName", subcategoryBean.getSubName());
        query.setParameter("categoryId", subcategoryBean.getCategory().getCatId());
        if (!query.getResultList().isEmpty()) {
            return "exists";
        }
        if(subcategoryBean.getSubName().isEmpty() || subcategoryBean.getSubName() == null)
            return "empty";
        Subcategory sub = new Subcategory();
        sub.setCategory(subcategoryBean.getCategory());
        sub.setSubName(subcategoryBean.getSubName());
        entityManager.persist(sub);
        return "success";
    }

    public boolean deleteSubCategory(SubcategoryBean subcategoryBean) {
        Query query = entityManager.createQuery("SELECT e FROM Subcategory e WHERE e.subName =:subName AND e.category.catId =:categoryId");
        query.setParameter("subName", subcategoryBean.getSubName());
        query.setParameter("categoryId", subcategoryBean.getCategory().getCatId());
        if (query.getResultList().isEmpty()) {
            return false;
        }
        Subcategory sub = (Subcategory) query.getSingleResult();
        entityManager.remove(sub);
        return true;
    }

    public String persistCategory(CategoryBean categoryBean) {
        Query query = entityManager.createQuery("SELECT e FROM Category e WHERE e.name =:name");
        query.setParameter("name", categoryBean.getName());
        if (!query.getResultList().isEmpty()) {
            return "exists";
        }
        if(categoryBean.getName().isEmpty() || categoryBean.getName() == null)
            return "empty";
        Category cat = new Category();
        cat.setName(categoryBean.getName());
        entityManager.persist(cat);
        return "success";
    }

    public boolean deleteCategory(CategoryBean categoryBean) {
        Query query = entityManager.createQuery("SELECT e FROM Category e WHERE e.name =:name");
        query.setParameter("name", categoryBean.getName());
        if (query.getResultList().isEmpty()) {
            return false;
        }
        Category cat = (Category) query.getSingleResult();
        entityManager.remove(cat);
        return true;
    }
}
