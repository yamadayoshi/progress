package com.progress.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(length=60)
	private String name;
	
	@Column(length=50)
	private String login;
	
	@Column(length=200)
	private String password;
	
	@Column(name="register_date", columnDefinition="DATETIME default CURRENT_TIMESTAMP")
	private LocalDateTime registerDate;
	
	// create table user_permissions
	@ManyToMany
	private List<Permition>	permition;
	
	// create table user_groups
	@ManyToMany
	private List<Group> group;

	private boolean active;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(LocalDateTime registerDate) {
		this.registerDate = registerDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public List<Permition> getPermition() {
		return permition;
	}

	public void setPermition(List<Permition> permition) {
		this.permition = permition;
	}

	public List<Group> getGroup() {
		return group;
	}

	public void setGroup(List<Group> group) {
		this.group = group;
	}	

	@Override
	public String toString() {		
		return "Id: " + getId() + " , Login: " + getLogin() + " , Name: " + getName();
	}

}
