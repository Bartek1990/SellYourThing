package model;

import java.io.File;
import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;


/**
 * The persistent class for the auction database table.
 * 
 */
@Entity
@Table(name = "auction")
public class Auction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="auction_id")
	private int auctionId;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="exp_date")
	private Date expDate;

	private String status;

	private String title;

	private String type;

	//bi-directional many-to-one association to Subcategory
	@ManyToOne
	@JoinColumn(name="Subcategoriessub_id")
	private Subcategory subcategory;

    @ManyToOne
    @JoinColumn(name="owner_id")
	private User user;

	//bi-directional many-to-one association to Biding
	@OneToMany(mappedBy="auction", cascade = CascadeType.ALL)
	private List<Biding> bidings;
        public double getHigherBid()
        {
            double higherBid = 0;
                    for(Biding bid : bidings)
                    {
                        if(bid.getCurrentPrice() > higherBid)
                            higherBid = bid.getCurrentPrice();
                    }
                    return higherBid;
        }

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="auction", cascade = CascadeType.ALL)
	private List<Comment> comments;

	//bi-directional many-to-one association to Grade
	@OneToMany(mappedBy="auction", cascade = CascadeType.ALL)
	private List<Grade> grades;

	//bi-directional many-to-one association to ProductImage
	@OneToMany(mappedBy="auction", cascade = CascadeType.ALL)
	private List<ProductImage> productImages;
        public String getFirstImageUrl(){
            if(!productImages.isEmpty() && productImages != null)
            {
                 ProductImage oneImage = productImages.get(0);
                 File f = new File("/images/"+oneImage.getUrl());
                 if(f.exists()) return oneImage.getUrl();
                 else return "no_image.jpg";
            }
            else return "no_image.jpg";
        }
	public Auction() {
	}

	public int getAuctionId() {
		return this.auctionId;
	}

	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExpDate() {
                if(expDate.compareTo(new Date())>0)
                    this.status = "0";
		return this.expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Subcategory getSubcategory() {
		return this.subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Biding> getBidings() {
		return this.bidings;
	}

	public void setBidings(List<Biding> bidings) {
		this.bidings = bidings;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Grade> getGrades() {
		return this.grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public List<ProductImage> getProductImages() {
		return this.productImages;
	}

	public void setProductImages(List<ProductImage> productImages) {
		this.productImages = productImages;
	}
}