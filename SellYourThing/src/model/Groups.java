package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the groups database table.
 * 
 */
@Entity
@Table(name="groups")
public class Groups implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="group_id")
	private int groupId;

	@Column(name="acc_level")
	private int accLevel;

	private String name;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="groups", cascade={CascadeType.ALL})
	private List<User> users;

	public Groups() {
	}

	public int getGroupId() {
		return this.groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getAccLevel() {
		return this.accLevel;
	}

	public void setAccLevel(int accLevel) {
		this.accLevel = accLevel;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}