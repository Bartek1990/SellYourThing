package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer userId;

	@Temporal(TemporalType.DATE)
	@Column(name="date_of_birth")
	private Date dateOfBirth;

	private String email;

	private String forname;

	private String name;

	private String password;

	private String pesel;

	@Temporal(TemporalType.DATE)
	@Column(name="register_date")
	private Date registerDate;

	//bi-directional many-to-many association to Auction
	@ManyToMany(mappedBy="users")
	private List<Auction> auctions;

	//bi-directional many-to-one association to Biding
	@OneToMany(mappedBy="user")
	private List<Biding> bidings;

	//bi-directional many-to-one association to Address
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="Addressaddress_id")
	private Address address;

	//bi-directional many-to-one association to Group
	@ManyToOne
	@JoinColumn(name="Groupgroup_id")
	private Groups group;

	public User() {
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getForname() {
		return this.forname;
	}

	public void setForname(String forname) {
		this.forname = forname;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPesel() {
		return this.pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public Date getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public List<Auction> getAuctions() {
		return this.auctions;
	}

	public void setAuctions(List<Auction> auctions) {
		this.auctions = auctions;
	}

	public List<Biding> getBidings() {
		return this.bidings;
	}

	public void setBidings(List<Biding> bidings) {
		this.bidings = bidings;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Groups getGroup() {
		return this.group;
	}

	public void setGroup(Groups group) {
		this.group = group;
	}

}