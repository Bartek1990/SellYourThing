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
	private int subId;

	@Column(name="sub_name")
	private String subName;

	//bi-directional many-to-one association to Auction
	@OneToMany(mappedBy="subcategory")
	private List<Auction> auctions;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="Categoriescat_id")
	private Category category;

	public Subcategory() {
	}

	public int getSubId() {
		return this.subId;
	}

	public void setSubId(int subId) {
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((subName == null) ? 0 : subName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (!this.subName.equals(((Subcategory) obj).subName))
			return false;
		return true;
	}

}