package com.progress.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="grupo")
public class Group implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String description;
	
	// ignores the table creation (user already created it)
	@ManyToMany(mappedBy="group")
	private List<User> user;

	// create table group_permition
	@ManyToMany
	private List<Permition> permition;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<User> getUsers() {
		return user;
	}

	public void setUsers(List<User> users) {
		this.user = users;
	}

	public List<Permition> getPermitions() {
		return permition;
	}

	public void setPermitions(List<Permition> permitions) {
		this.permition = permitions;
	}
	
	@Override
	public String toString() {
		return "Id: " + getId() + " , Description; " + getDescription();
	}

}
