package com.ejb.eao;

import com.beans.AuctionBean;
import com.beans.ProductImageBean;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Auction;
import model.Biding;
import model.ProductImage;

import model.User;

@Stateful
@LocalBean
public class AuctionEAO {

    @PersistenceContext()
    EntityManager entityManager;

    public AuctionEAO() {
    }

    public List<AuctionBean> getAllAuctions() {
        Query query = entityManager.createQuery("SELECT e FROM Auction e");
        return query.getResultList();
    }
    public boolean deleteAuction(Auction auction){
        Query query = entityManager.createQuery("SELECT e FROM Auction e WHERE e.auctionId=:id").setParameter("id", auction.getAuctionId());
        Auction a = (Auction)query.getSingleResult();
        a.setStatus("0");
        entityManager.merge(a);
        return true;
    }
    public User getUserByEmail(String email) {
        System.out.println("dasdasdasdas " + email);
        return (User) entityManager.createQuery(
                "SELECT e FROM User e WHERE e.email=:emailVal")
                .setParameter("emailVal", email)
                .getSingleResult();

    }

    public void persistBid(double bid, int auctionId, String userEmail) {
        Biding newBid = new Biding();
        User user = getUserByEmail(userEmail);
        Auction auction = (Auction) entityManager.createQuery("SELECT e FROM Auction e WHERE e.auctionId=:auctionId").setParameter("auctionId", auctionId).getSingleResult();
        newBid.setCurrentPrice(bid);
        newBid.setAuction(auction);
        newBid.setUser(user);
        entityManager.persist(newBid);
    }

    public boolean persistAuction(AuctionBean auctionBean) {
        try {
            Auction auction;
            auction = new Auction();
            auction.setTitle(auctionBean.getTitle());
            auction.setDescription(auctionBean.getDescription());
            auction.setType(auctionBean.getType());
            auction.setExpDate(auctionBean.getExpDate());
            auction.setStatus(auctionBean.getStatus());
            auction.setUser(this.getUserByEmail(auctionBean.getUserBean().getEmail()));
            List<Biding> bidings = new ArrayList<Biding>();
            Biding newBid = new Biding();
            newBid.setAuction(auction);
            newBid.setCurrentPrice(Double.parseDouble(auctionBean.getPrice()));
            newBid.setUser(auctionBean.getUserA());
            bidings.add(newBid);
            auction.setBidings(bidings);
            auction.setSubcategory(auctionBean.getSubCategory());
            //uzupełnienie obrazków
            List<ProductImage> productImages = new ArrayList<ProductImage>();
            for (ProductImageBean imageBean : auctionBean.getProductImages()) {
                ProductImage prodIm = new ProductImage();
                prodIm.setUrl(imageBean.getUrl());
                prodIm.setTitle(imageBean.getTitle());
                prodIm.setAuction(auction);
                productImages.add(prodIm);
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
    //Nie ma wielkiej walidacji
    public boolean persistChangedAuction(AuctionBean auctionBean)
    {
        Auction aucToChange = (Auction) entityManager.createQuery("SELECT c FROM Auction c WHERE c.title=:aTitle").setParameter("aTitle", auctionBean.getTitle()).setMaxResults(1).getSingleResult();
        if(auctionBean.getTitle() != null || auctionBean.getTitle() != "")
        {
            aucToChange.setTitle(auctionBean.getTitle());
        } else if(auctionBean.getDescription() != null || auctionBean.getDescription()!= "")
        {
            aucToChange.setDescription(auctionBean.getDescription());
        }
        return true;
    }
    public List<Auction> getMyAuctions(String username)
    {
        Query query = entityManager.createQuery("SELECT e FROM Auction e WHERE e.user.email=:emailVal AND e.status = '0'");
        query.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return query.setParameter("emailVal", username).getResultList();
    }
    public List<Auction> getWonAuctions(String username)
    {
        Query query = entityManager.createQuery("SELECT DISTINCT e.auction FROM Biding e WHERE e.user.email=:emailVal AND e.auction.status='0' AND e.auction.user.email <> :emailVal");
        query.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return query.setParameter("emailVal", username).getResultList();
    }
    public List<Auction> getActAuctions(String username)
    {
        Query query = entityManager.createQuery("SELECT DISTINCT e.auction FROM Biding e WHERE e.user.email=:emailVal AND e.auction.user.email <> :emailVal AND e.auction.status='1'");
        query.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return query.setParameter("emailVal", username).getResultList();
    }
}
