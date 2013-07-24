package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the auction_bot database table.
 * 
 */
@Entity
@Table(name="auction_bot")
public class AuctionBot implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bot_id")
	private Integer botId;

	@Column(name="interval_b")
	private double intervalB;

	@Column(name="limit_b")
	private float limitB;

	private double step;

	//bi-directional many-to-one association to Biding
	@OneToMany(mappedBy="auctionBot")
	private List<Biding> bidings;

	public AuctionBot() {
	}

	public Integer getBotId() {
		return this.botId;
	}

	public void setBotId(Integer botId) {
		this.botId = botId;
	}

	public double getIntervalB() {
		return this.intervalB;
	}

	public void setIntervalB(double intervalB) {
		this.intervalB = intervalB;
	}

	public float getLimitB() {
		return this.limitB;
	}

	public void setLimitB(float limitB) {
		this.limitB = limitB;
	}

	public double getStep() {
		return this.step;
	}

	public void setStep(double step) {
		this.step = step;
	}

	public List<Biding> getBidings() {
		return this.bidings;
	}

	public void setBidings(List<Biding> bidings) {
		this.bidings = bidings;
	}

}