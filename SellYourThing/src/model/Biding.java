package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the biding database table.
 * 
 */
@Entity
public class Biding implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="biding_id")
	private int bidingId;

	@Column(name="current_price")
	private double currentPrice;

	//bi-directional many-to-one association to Auction
	@ManyToOne
	@JoinColumn(name="Auctionauction_id")
	private Auction auction;

	//bi-directional many-to-one association to AuctionBot
	@ManyToOne
	@JoinColumn(name="Auction_botbot_id")
	private AuctionBot auctionBot;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="Useruser_id")
	private User user;

	public Biding() {
	}

	public int getBidingId() {
		return this.bidingId;
	}

	public void setBidingId(int bidingId) {
		this.bidingId = bidingId;
	}

	public double getCurrentPrice() {
		return this.currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public Auction getAuction() {
		return this.auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public AuctionBot getAuctionBot() {
		return this.auctionBot;
	}

	public void setAuctionBot(AuctionBot auctionBot) {
		this.auctionBot = auctionBot;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}