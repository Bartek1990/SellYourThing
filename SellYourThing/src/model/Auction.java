package model;

import java.io.File;
import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the auction database table.
 * 
 */
@Entity
public class Auction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="auction_id")
	private int auctionId;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="exp_date")
	private Date expDate;

	private String status;

	private String title;

	private String type;

	//bi-directional many-to-one association to Subcategory
	@ManyToOne
	@JoinColumn(name="Subcategoriessub_id")
	private Subcategory subcategory;

	//bi-directional many-to-many association to User
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(
		name="auction_user"
		, joinColumns={
			@JoinColumn(name="Auctionauction_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Useruser_id")
			}
		)
	private List<User> users;

	//bi-directional many-to-one association to Biding
	@OneToMany(mappedBy="auction", cascade = CascadeType.PERSIST)
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
	@OneToMany(mappedBy="auction")
	private List<Comment> comments;

	//bi-directional many-to-one association to Grade
	@OneToMany(mappedBy="auction")
	private List<Grade> grades;

	//bi-directional many-to-one association to ProductImage
	@OneToMany(mappedBy="auction", cascade = CascadeType.PERSIST)
	private List<ProductImage> productImages;
        public String getFirstImageUrl(){
            if(!productImages.isEmpty() && productImages != null)
            {
                 ProductImage oneImage = productImages.get(0);
                 File f = new File("C:/images/"+oneImage.getUrl());
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

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
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