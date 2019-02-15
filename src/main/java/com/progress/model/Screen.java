package com.progress.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="screen")
public class Screen {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="screen_id")
	private int ScreenId;	
	
	@Column(name="screen_name", length=50)
	private String screenName;	
		
	@Column(name="screen_description")
	@Lob
	private String screenDescription;
	
	public Screen() {
		
	}
	
	public Screen(int id) {
		setScreenId(id);
	}
	
	public int getScreenId() {
		return ScreenId;
	}
	public void setScreenId(int screenId) {
		ScreenId = screenId;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public String getScreenDescription() {
		return screenDescription;
	}
	public void setScreenDescription(String screenDescription) {
		this.screenDescription = screenDescription;
	}
}
