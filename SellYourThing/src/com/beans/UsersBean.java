package com.beans;

import com.ejb.eao.CategoryEAO;
import com.ejb.eao.UserEAO;
import java.security.acl.Group;
import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;
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
import javax.transaction.UserTransaction;
import model.Category;
import model.GroupLvl;
import model.User;

@ManagedBean(name = "usersBean")
public class UsersBean {

    @Resource
    UserTransaction ut;
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

    public void banUser() throws Exception {
        Query query = entityManager.createQuery("SELECT e FROM User e WHERE e.email =:email");
        query.setParameter("email", user.getEmail());
        User usr = (User) query.getSingleResult();
        if (usr.getBanned() == 1) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Użytkownik jest już zbanowany", null));
            return;
        }
        if (usr.getGroupLvl().getAccLevel() == 3) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Nie możesz zbanować administratora", null));
            return;
        }

        usr.setBanned(1);
        ut.begin();
        entityManager.merge(usr);
        ut.commit();
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Użytkownik został zbanowany", null));
    }
        public void unbanUser() throws Exception {
        Query query = entityManager.createQuery("SELECT e FROM User e WHERE e.email =:email");
        query.setParameter("email", user.getEmail());
        User usr = (User) query.getSingleResult();
        if (usr.getBanned() == 0) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Użytkownik nie jest zbanowany", null));
            return;
        }
        if (usr.getGroupLvl().getAccLevel() == 3) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Nie możesz zbanować administratora", null));
            return;
        }

        usr.setBanned(0);
        ut.begin();
        entityManager.merge(usr);
        ut.commit();
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Użytkownik został odbanowany", null));
    }

    public void setAdmin() throws Exception {
        Query query = entityManager.createQuery("SELECT e FROM User e WHERE e.email =:email");
        query.setParameter("email", user.getEmail());
        User usr = (User) query.getSingleResult();
        if (usr.getGroupLvl().getAccLevel() == 3) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Użytkownik jest administratorem", null));
            return;
        }
        Query queryNew = entityManager.createQuery("SELECT e FROM GroupLvl e WHERE e.groupId =:id");
        queryNew.setParameter("id", 1);
        GroupLvl group = (GroupLvl) queryNew.getSingleResult();
        usr.setGroupLvl(group);
        ut.begin();
        entityManager.merge(usr);
        ut.commit();
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Użytkownik otrzymał uprawnienia administratora", null));
    }
}
