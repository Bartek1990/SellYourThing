package com.beans;

import java.security.Principal;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean(name="loginBean")
public class LoginBean {
	private String email;
	private String password;
	
	public LoginBean()
	{
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	    if(session != null){
	        session.invalidate();
	    }
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
	public String login()
	{
		String message = "";
		String navTo = "";
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		try {
			request.login(email, password);
			Principal principal = request.getUserPrincipal();
			
			if(request.isUserInRole("Administrator"))
			{
				message = "U¿ytkownik: " + principal.getName();
				navTo = "admin";
			} else if(request.isUserInRole("Moderator"))
			{
				message = "U¿ytkownik: " + principal.getName();
				navTo = "moderator";
			} else if(request.isUserInRole("User"))
			{
				message = "U¿ytkownik: " + principal.getName();
				navTo = "user";
			}
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
	        return navTo;
		} catch (ServletException e) {
            FacesContext.getCurrentInstance().addMessage(
            		null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
            				"Wyst¹pi³ b³¹d, niezalogowano!", null));
            e.printStackTrace();
        }
		return "failure";
	}
	public void logout()
	{
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(session != null){
            session.invalidate();
        }
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/login.xhtml");
	}
	
}
