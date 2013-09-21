package com.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@ManagedBean
public class AuctionListBean 
{
    @PersistenceContext()
    EntityManager entityManager;
    private List<AuctionBean> auctionList;
	public List<AuctionBean> getAuctionList() {
		Query query = entityManager.createQuery("SELECT e FROM Auction e");
		auctionList = (List<AuctionBean>) query.getResultList();
		return auctionList;
	}
	public Auction getSingleAuction()
	{
		
	}
}
