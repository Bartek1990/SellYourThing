package com.beans;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Auction;

@ViewScoped
@ManagedBean
public class AuctionListBean {

    @PersistenceContext()
    EntityManager entityManager;
    private List<Auction> auctionList;

    public List<Auction> getAuctionList() {
        Query query = entityManager.createQuery("SELECT e FROM Auction e WHERE e.status=1");
        query.setHint("javax.persistence.cache.storeMode", "REFRESH");
        auctionList = (List<Auction>) query.getResultList();
        return auctionList;
    }
}
