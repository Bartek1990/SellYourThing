package com.beans;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import com.ejb.eao.RegistrationEAO;


@ManagedBean(name="regBean")
public class RegistrationBean {
	private String username;
	private String userforname;
	private String password;
	private String confirmpassword;
	private String pesel;
	private String email;
	private Date dob;
	private String confirmemail;
	private String street;
	private String zip;
	private String city;
	private String province;
	private String country;
	@EJB
	RegistrationEAO service;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserforname() {
		return userforname;
	}
	public void setUserforname(String userforname) {
		this.userforname = userforname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public String getPesel() {
		return pesel;
	}
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getConfirmemail() {
		return confirmemail;
	}
	public void setConfirmemail(String confirmemail) {
		this.confirmemail = confirmemail;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public void emailValidator(FacesContext context, UIComponent component, Object value)
	{
		UIInput emailComponent = (UIInput) component.getAttributes().get("email");
		String email = (String) emailComponent.getValue();
		String confirmEmail = (String) value;
		
		if(email.indexOf("@") < 0 || email.indexOf("@") == email.length()-1)
		{
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niepoprawny adres email, wpisz adres w formacie: user@domain", null));
	    }
		else if(!email.equals(confirmEmail))
		{
	        throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Adresy email nie pasują!", null));
		}
	}
	private void addMessage(FacesMessage message)
	{
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	public String addUser()
	{
		if(service.persistUser(this))
		{
			addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Użytkownik zarejestrowany poprawnie!", null));
	        return "success";
		}
		addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd przy rejestracji użytkownika!", null));
        return "failure";
	}
    public String changeGroup(String newGroupLvl, String userEmail)
    {
        service.setGroupLvl(newGroupLvl, userEmail);
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Grupa zmieniona poprawnie!", null));
        return "success";
    }
	
	
}
