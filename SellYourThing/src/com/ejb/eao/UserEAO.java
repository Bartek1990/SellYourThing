package com.ejb.eao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.beans.CategoryBean;
import com.beans.SubcategoryBean;
import com.beans.UsersBean;
import javax.annotation.Resource;
import javax.transaction.NotSupportedException;
import javax.transaction.UserTransaction;
import model.Category;
import model.Subcategory;
import model.User;

@Stateless
@LocalBean
public class UserEAO {
    @Resource
    UserTransaction ut;
    @PersistenceContext()
    EntityManager entityManager;

    public UserEAO() {
    }

    public boolean banUser(UsersBean user) throws Exception {
        Query query = entityManager.createQuery("SELECT e FROM User e WHERE e.email =:email");
        query.setParameter("email", user.getUser().getEmail());
        User usr = (User) query.getSingleResult();
        //ut.begin();
        entityManager.merge(usr);
        //ut.commit();
        return true;
    }
    
}
