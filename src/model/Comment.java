package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the comment database table.
 * 
 */
@Entity
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="comment_id")
	private int commentId;

	private String content;

	//bi-directional many-to-one association to Auction
	@ManyToOne
	@JoinColumn(name="Auctionauction_id")
	private Auction auction;

	public Comment() {
	}

	public int getCommentId() {
		return this.commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Auction getAuction() {
		return this.auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

}