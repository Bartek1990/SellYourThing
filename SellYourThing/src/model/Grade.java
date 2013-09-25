package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the grade database table.
 * 
 */
@Entity
public class Grade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="grade_id")
	private int gradeId;

	@Column(name="grade_lvl")
	private int gradeLvl;

	//bi-directional many-to-one association to Auction
	@ManyToOne
	@JoinColumn(name="Auctionauction_id")
	private Auction auction;

	public Grade() {
	}

	public int getGradeId() {
		return this.gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public int getGradeLvl() {
		return this.gradeLvl;
	}

	public void setGradeLvl(int gradeLvl) {
		this.gradeLvl = gradeLvl;
	}

	public Auction getAuction() {
		return this.auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

}