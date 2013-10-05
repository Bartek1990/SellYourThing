package com.beans;

import java.security.Principal;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.User;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean {

    private String email;
    private String password;
    @PersistenceContext()
    EntityManager entityManager;

    public LoginBean() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        String message = "";
        String navTo = "";


        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            Query query = entityManager.createQuery("SELECT c FROM User c WHERE c.email=:email");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            query.setParameter("email", email);
            User u = (User)query.getSingleResult();
            if(u.getBanned() == 1)
            {
                FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Zostałeś zbanowany, skontaktuj się z administracją", null));
                return "failure";
            }
            request.login(email, password);
            Principal principal = request.getUserPrincipal();
            if (request.isUserInRole("Administrator")) {
                message = "Użytkownik: " + principal.getName();
                navTo = "admin";
            } else if (request.isUserInRole("Moderator")) {
                message = "Użytkownik: " + principal.getName();
                navTo = "moderator";
            } else if (request.isUserInRole("User")) {
                message = "Użytkownik: " + principal.getName();
                navTo = "user";
            }
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
            return navTo;
        } catch (ServletException e) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Wystąpił błąd. Nie zalogowano!", null));
            e.printStackTrace();
        }
        return "failure";
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        System.out.println("INFO Z LOGINBEAN: wylogowano");
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/login.xhtml");
        return "loggedout";
    }
}
