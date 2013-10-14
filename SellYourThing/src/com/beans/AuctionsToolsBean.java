package com.beans;

import com.ejb.eao.AuctionEAO;
import model.Auction;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Bartek Grzebinoga
 * Date: 08.10.13
 * Time: 19:23
 * To change this template use File | Settings | File Templates.
 */
@RequestScoped
@ManagedBean
public class AuctionsToolsBean {
    public List<Auction> myAuctions;
    public Auction selectedAuc;
    public List<Auction> wonAuctions;
    public List<Auction> actAuctions;

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean userBean;
    @EJB
    AuctionEAO service;

    public AuctionsToolsBean() {

    }

    public Auction getSelectedAuc() {

        return selectedAuc;
    }

    public void setSelectedAuc(Auction selectedAuc) {
        if(this.selectedAuc == null)
            this.selectedAuc = selectedAuc;
    }

    public LoginBean getUserBean() {
        return userBean;
    }

    public void setUserBean(LoginBean userBean) {
        this.userBean = userBean;
    }

    public List<Auction> getActAuctions() {
        if(actAuctions == null)
            populateActAuctions();
        return actAuctions;
    }

    public void setActAuctions(List<Auction> actAuctions) {
        this.actAuctions = actAuctions;
    }

    public List<Auction> getWonAuctions() {
        if(wonAuctions == null)
            populateWonAuctions();
        return wonAuctions;
    }

    public void setWonAuctions(List<Auction> wonAuctions) {
        this.wonAuctions = wonAuctions;
    }

    public List<Auction> getMyAuctions() {
        if(myAuctions == null)
            populateMyAuctions();
        return myAuctions;
    }
    private void populateMyAuctions()
    {
        myAuctions = service.getMyAuctions(userBean.getEmail());
    }
    private void populateWonAuctions()
    {
        wonAuctions = service.getWonAuctions(userBean.getEmail());
    }
    private void populateActAuctions()
    {
        actAuctions = service.getActAuctions(userBean.getEmail());
    }
    public Date todaysDate()
    {
        return new Date();
    }
}
