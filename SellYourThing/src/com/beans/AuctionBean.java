package com.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import model.ProductImage;

import com.ejb.eao.AuctionEAO;
import com.ejb.eao.RegistrationEAO;

@ManagedBean(name = "aucBean")
@SessionScoped
public class AuctionBean {

    private String title;
    private String description;
    private String type;
    private Date expDate;
    private int subcategoryId;
    private String status;
    private int auctionId;
    private String imgName;
    private List<ProductImage> productImages = new ArrayList<ProductImage>();
    
   
    
	private List<String> namesImg = new ArrayList<String>();
    @EJB
    AuctionEAO service;

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

        public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public int getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(int subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	
	public List<ProductImage> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<ProductImage> productImages) {
		this.productImages = productImages;
	}
	
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public String genNanTime()
    {	
    	
    	String nano = System.nanoTime()+"";
    	namesImg.add(nano);
    	return nano;
    }
    public void addImages() 
    {
        ProductImage img = new ProductImage();
        img.setTitle(imgName);
        img.setUrl("/user/upload/"+namesImg.get(namesImg.size()-1)+"_image.jpg");
        productImages.add(img);
       
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dodano obrazek "+imgName+"!", null));
        
    }
    public String acceptImages()
    {
    	return "accept";
    }
    public String addAuction() {
        if (service.persistAuction(this)) {
            addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Aukcja dodana!", null));
            return "add";
        }
        addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Blad podczas dodawania aukcji!", null));
        return "failure";
    }

	

	
    
}
