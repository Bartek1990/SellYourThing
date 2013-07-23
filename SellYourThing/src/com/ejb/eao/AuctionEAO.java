package com.ejb.eao;

import com.beans.AuctionBean;
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
            //pobieram narazie id kategori
            String subCategoryName = "Subkategoria";	
            
            Query query = this.entityManager.createQuery("SELECT c FROM Subcategory c WHERE c.subName=?1");
            query.setParameter("1", subCategoryName);
            Object nowy = query.getSingleResult();
            auction.setSubcategory((Subcategory) nowy);
            //uzupe³nienie obrazków
            List<ProductImage> productImages = auctionBean.getProductImages();
            for(int i=0;i<productImages.size();i++)
            {
            	System.out.println(productImages.get(i).getUrl());
            	productImages.get(i).setAuction(auction);
            }
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
