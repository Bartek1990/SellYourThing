package com.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Auction;
import model.Biding;

@ManagedBean
public class AuctionListBean 
{
    @PersistenceContext()
    EntityManager entityManager;
    private List<AuctionBean> auctsionList;
	public List<AuctionBean> getAuctsionList() {
		//Query query = entityManager.createQuery("SELECT e FROM Auction e");
		//auctionList = (List<AuctionBean>) query.getResultList();
		return auctsionList;
	}
}
