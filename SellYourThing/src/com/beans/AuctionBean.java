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

import model.Biding;
import model.ProductImage;

import javax.servlet.http.Part;

import java.io.IOException;

import com.ejb.eao.AuctionEAO;
import com.ejb.eao.RegistrationEAO;

@ManagedBean(name = "AucBean")
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
    private Part image;
    private List<Biding> bidings = new ArrayList<Biding>();
    private String test;
    private List<AuctionBean> auctionList;
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
    }public void addImages() 
    {
    	String url = new String(getFilename(image));
    	try 
    	{
			image.write(url);
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
        ProductImage img = new ProductImage();
        img.setTitle("tu bedzie tytul");
        img.setUrl(url);
        productImages.add(img);
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dodano obrazek "+img.getTitle()+"!", null));    
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

	public Part getImage() {
		return image;
	}

	public void setImage(Part image) {
		this.image = image;
	}
	private static String getFilename(Part part) {  
        for (String cd : part.getHeader("content-disposition").split(";")) {  
            if (cd.trim().startsWith("filename")) {  
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");  
                return + System.nanoTime() + "_" + filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.  
            }  
        }  
        return null;  
    }
	public String test(){return "aaa";}
	public double getHigherBid()
	{
		double higherBid = 0;
		for(Biding a : bidings)
		{
			if(a.getCurrentPrice() > higherBid) 
				higherBid = a.getCurrentPrice();
		}
		return higherBid;
	}

	public List<Biding> getBidings() {
		return bidings;
	}

	public void setBidings(List<Biding> bidings) {
		this.bidings = bidings;
	}

	public String getTest() {
		return "lala";
	}

	public void setTest(String test) {
		this.test = test;
	}

	public List<AuctionBean> getAuctionList() {
		return auctionList;
	}

	public void setAuctionList(List<AuctionBean> auctionList) {
		this.auctionList = service.getAllAuctions();
	}
}