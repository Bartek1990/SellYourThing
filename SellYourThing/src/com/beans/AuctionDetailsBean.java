package com.beans;

import com.ejb.eao.AuctionEAO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Auction;
import model.Biding;
import model.Category;
import model.ProductImage;
import model.Subcategory;

@SessionScoped
@ManagedBean
public class AuctionDetailsBean {

    private String title;
    private String description;
    private double price;
    private Date expDate;
    private Category category;
    private Subcategory subCategory;
    private List<ProductImage> productImages = new ArrayList<ProductImage>();
    private String mainImageUrl;
    private String message = "";
    private Date timeLeft;
    private int auctionId;
    @EJB
    AuctionEAO service;
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean userBean;

    public LoginBean getUserBean() {
        return userBean;
    }

    public void setUserBean(LoginBean userBean) {
        this.userBean = userBean;
    }

    public Date getTimeLeft() {
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        today.add(Calendar.DAY_OF_WEEK, 1);
        long left = expDate.getTime() - today.getTime().getTime();
        timeLeft = new Date(left);
        return timeLeft;
    }

    public void setTimeLeft(Date timeLeft) {
        this.timeLeft = timeLeft;
    }

    public String details(Auction auction) {
        title = auction.getTitle();
        description = auction.getDescription();
        expDate = auction.getExpDate();
        subCategory = auction.getSubcategory();
        productImages = auction.getProductImages();
        price = auction.getHigherBid();
        if (!productImages.isEmpty()) {
            mainImageUrl = productImages.get(0).getUrl();
        } else {
            mainImageUrl = "no_image.jpg";
        }
        auctionId = auction.getAuctionId();
        return "auctionDetails";
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {

        if (userBean.getEmail() == null) {
            message = "Zaloguj się by licytować!";
        } else {
            if (price >= (this.price + 1)) {
                service.persistBid(price, auctionId, userBean.getEmail());
                this.price = price;
                message = "Gratulacje, Twoja ofera jest najwyższa!";
            } else {
                message = "Twoja oferta musi być przynajmniej o 1zł wyższa od aktualnej";
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Subcategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(Subcategory subCategory) {
        this.subCategory = subCategory;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String url) {
        this.mainImageUrl = url;
    }
}
