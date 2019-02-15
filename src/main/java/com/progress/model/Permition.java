package com.progress.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Permition  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;	
	
	private String description;
	
	// ignores the table creation (the owner is user)
	@ManyToMany(mappedBy="permition")
	private List<User> users;

	// ignores the table creation (the owner is group)	
	@ManyToMany(mappedBy="permition")
	private List<Group> groups;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<User> getUser() {
		return users;
	}

	public void setUser(List<User> user) {
		this.users = user;
	}

	public List<Group> getGroup() {
		return groups;
	}

	public void setGroup(List<Group> group) {
		this.groups = group;
	}
	
	@Override
	public String toString() {
		return "Id: " + getId() + " ,  Description: " + getDescription();
	}
}
