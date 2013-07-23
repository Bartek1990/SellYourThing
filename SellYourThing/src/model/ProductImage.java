package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the product_images database table.
 * 
 */
@Entity
@Table(name="product_images")
public class ProductImage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="img_id")
	private Integer imgId;

	private String title;

	private String url;

	//bi-directional many-to-one association to Auction
	@ManyToOne
	@JoinColumn(name="Auctionauction_id")
	private Auction auction;

	public ProductImage() {
	}

	public Integer getImgId() {
		return this.imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Auction getAuction() {
		return this.auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

}