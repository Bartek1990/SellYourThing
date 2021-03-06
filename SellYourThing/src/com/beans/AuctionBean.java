package com.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import static java.nio.file.StandardCopyOption.*;
import javax.servlet.http.Part;

import model.Biding;
import model.Category;
import model.Subcategory;

import java.io.IOException;

import com.ejb.eao.AuctionEAO;
import com.ejb.eao.CategoryEAO;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

@ViewScoped
@ManagedBean
public class AuctionBean {

    private String title;
    private String description;
    private String type = "1";
    private String price;
    private Date expDate;
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean userBean;
    private Category category = new Category();
    private Subcategory subCategory;
    private String status = "1";
    private int auctionId;
    private String imgName;
    private List<ProductImageBean> productImages = new ArrayList<ProductImageBean>();
    private List<AuctionBean> auctionList;
    private List<Part> imagesList = new ArrayList<Part>();
    private Part image;
    @EJB
    AuctionEAO service;
    private User userA;

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

    public Subcategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(Subcategory subCategory) {
        this.subCategory = subCategory;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = "1";
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
        System.out.print(this.expDate.toString());
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUserA() {
        return userA;
    }

    public void setUserA(User user) {
        this.userA = user;
    }

    public LoginBean getUserBean() {
        return userBean;
    }

    public void setUserBean(LoginBean userBean) {
        this.userBean = userBean;
    }

    public String getImgName() {
        return imgName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public List<ProductImageBean> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImageBean> productImages) {
        this.productImages = productImages;
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String addAuction() {
        this.setUserA(service.getUserByEmail(userBean.getEmail()));
        if (service.persistAuction(this)) {
            addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Aukcja dodana!", null));
            return "add";
        }
        addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Blad podczas dodawania aukcji!", null));
        return "failure";
    }

    public List<Part> getImagesList() {
        return imagesList;
    }

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.imagesList.add(image);
        String url = generateFilename(image);
        Path path = Paths.get("/images/" + url);
        try {
            Files.copy(image.getInputStream(), path, REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(AuctionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        ProductImageBean img = new ProductImageBean();
        img.setTitle("tu bedzie tytul");
        img.setUrl(url);
        productImages.add(img);
        this.image = image;
    }

    private static String generateFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return +System.nanoTime() + "_" + filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }

    public enum Days {
        IN_2_MIN("2 minuty (TEST)", 0),
        IN_1_DAY("1 dzień", 1),
        IN_4_DAYS("4 dni", 4),
        IN_7_DAYS("7 dni", 7),
        IN_14_DAYS("14 dni", 14);
        private String label;
        private int days;
        private Days(String label, int days) {
            this.label = label;
            this.days = days;
        }

        public String getLabel() {
            return label;
        }

        public Date calculateExpDate() {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            if(this.days == 0)
                cal.add(Calendar.MINUTE, 2);
            else
                cal.add(Calendar.DATE, this.days);
            return cal.getTime();
        }
    }
    private Days choosenOption;

    public void setChoosenOption(Days choosenOption) {
        this.choosenOption = choosenOption;
        expDate = choosenOption.calculateExpDate();
    }

    public Days getChoosenOption() {
        return choosenOption;
    }

    public Days[] getDays() {
        return Days.values();
    }

    public List<AuctionBean> getAuctionList() {
        return auctionList;
    }

    public void setAuctionList(List<AuctionBean> auctionList) {
        this.auctionList = service.getAllAuctions();
    }
}