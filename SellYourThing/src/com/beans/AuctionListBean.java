package com.beans;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import model.Auction;

@RequestScoped
@ManagedBean
public class AuctionListBean {

    @PersistenceContext()
    EntityManager entityManager;
    private List<Auction> auctionList;
    @Resource
    UserTransaction ut;
    @ManagedProperty(value = "#{param.subcategory}")
    String subcategory;
    @ManagedProperty(value = "#{param.category}")
    String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public List<Auction> getAuctionList() throws Exception {
        Query query = entityManager.createQuery("SELECT e FROM Auction e WHERE e.status=1");
        query.setHint("javax.persistence.cache.storeMode", "REFRESH");
        auctionList = (List<Auction>) query.getResultList();
        for (Auction a : auctionList) {
            Date today = new Date();
            System.out.println("compare: " + a.getExpDate() + " and " + today);
            if (a.getExpDate().compareTo(new Date()) < 0) {
                a.setStatus("0");
                ut.begin();
                entityManager.joinTransaction();
                entityManager.merge(a);
                ut.commit();
            }
        }
        Query newQuery;
        if (category == null) {
            newQuery = entityManager.createQuery("SELECT e FROM Auction e WHERE e.status=1");
        } else if(subcategory == null){
            newQuery = entityManager.createQuery("SELECT e FROM Auction e WHERE e.status=1 AND e.subcategory.category.name = :category");
            newQuery.setParameter("category", category);
        } else{
            newQuery = entityManager.createQuery("SELECT e FROM Auction e WHERE e.status=1 AND e.subcategory.subName = :subcategory");
            newQuery.setParameter("subcategory", subcategory);
        }
        newQuery.setHint("javax.persistence.cache.storeMode", "REFRESH");
        auctionList = (List<Auction>) newQuery.getResultList();
        return auctionList;
    }
}
