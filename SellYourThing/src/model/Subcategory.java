package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the subcategories database table.
 * 
 */
@Entity
@Table(name="subcategories")
public class Subcategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sub_id")
	private Integer subId;

	@Column(name="sub_name")
	private String subName;

	//bi-directional many-to-one association to Auction
	@OneToMany(mappedBy="subcategory", cascade={CascadeType.ALL})
	private List<Auction> auctions;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="Categoriescat_id")
	private Category category;

	public Subcategory() {
	}

	public Integer getSubId() {
		return this.subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

	public String getSubName() {
		return this.subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public List<Auction> getAuctions() {
		return this.auctions;
	}

	public void setAuctions(List<Auction> auctions) {
		this.auctions = auctions;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}