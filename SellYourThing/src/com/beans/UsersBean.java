package com.beans;

import com.ejb.eao.CategoryEAO;
import com.ejb.eao.UserEAO;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import model.Category;
import model.User;

@ManagedBean(name="usersBean")
public class UsersBean {

    @PersistenceContext()
    EntityManager entityManager;
    @EJB
    UserEAO service;
    private List<User> users;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        Query query = entityManager.createQuery("SELECT e FROM User e");
        query.setHint("javax.persistence.cache.storeMode", "REFRESH");
        users = (List<User>) query.getResultList();
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    public void deleteUser()
    {
        System.out.println("jestem");
        service.deleteUser(this);
    }
    
}
