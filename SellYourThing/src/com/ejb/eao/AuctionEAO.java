package com.ejb.eao;

import com.beans.AuctionBean;
import com.beans.ProductImageBean;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import model.Auction;
import model.Biding;
import model.Subcategory;
import model.ProductImage;
import java.io.File;
import java.io.FileInputStream;

import javax.annotation.Resource;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


@Stateful
@LocalBean

public class AuctionEAO {


    @PersistenceContext()
    EntityManager entityManager;
    
    public AuctionEAO() 
    {
    }
    public List<AuctionBean> getAllAuctions(){
    	Query query = entityManager.createQuery("SELECT e FROM Auction e");
		return query.getResultList();
    }
    public boolean persistAuction(AuctionBean auctionBean) {
        try 
        {
        	Auction auction;
            auction = new Auction();
            auction.setTitle(auctionBean.getTitle());
            auction.setDescription(auctionBean.getDescription());
            auction.setType(auctionBean.getType());
            auction.setExpDate(auctionBean.getExpDate());
            auction.setStatus(auctionBean.getStatus());
            List<Biding> bidings = new ArrayList<Biding>();
            Biding newBid = new Biding();
            newBid.setAuction(auction);
            newBid.setCurrentPrice(Double.parseDouble(auctionBean.getPrice()));
            
            bidings.add(newBid);
            auction.setBidings(bidings);
            System.out.println("DRUKUJE: " + auctionBean.getSubCategory());
            auction.setSubcategory(auctionBean.getSubCategory());
            //uzupe�nienie obrazk�w
            List<ProductImage> productImages = new ArrayList<ProductImage>();
            for(ProductImageBean imageBean : auctionBean.getProductImages())
            {
                ProductImage prodIm = new ProductImage();
                prodIm.setUrl(imageBean.getUrl());
                prodIm.setTitle(imageBean.getTitle());
                prodIm.setAuction(auction);
                productImages.add(prodIm);
            }
            System.out.println("liczba obrazkow: " + productImages.size());
            auction.setProductImages(productImages);
            
            entityManager.persist(auction);
            return true;
        } catch (ClassCastException e) {
            System.out.println("Class Cast Exception");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal Argument Exception");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Generic Exception");
            e.printStackTrace();
        }
        return false;
    }
}
