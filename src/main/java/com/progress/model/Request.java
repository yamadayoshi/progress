package com.progress.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="request")
public class Request {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="request_id")
	private int requestId;
	
	@Column(name="request_title", length=50)
	private String requestTitle;
	
	@Column(name="request_client_description")
	@Lob
	private String requestClientDescription;

	@Column(name="request_development_description")
	@Lob
	private String requestDevDescription;
	
	@Column(name="request_status", columnDefinition="varchar(10) default 'Open'")	
	private String status;
	
	@Column(name="request_entry_date", columnDefinition="DATETIME default CURRENT_TIMESTAMP")		
	private LocalDateTime requestEntryDate;
	
	@Column(name="request_development_date")
	private LocalDateTime requestDevDate;	
		
	@Column(name="request_validation_date")
	private LocalDateTime requestValidationDate;		
	
	@Column(name="request_implementation_date")
	private LocalDateTime requestImplementationDate;
	
	@ManyToOne
//	@Column(name="request_client")
	@JoinColumn(name="client_id")
	private Client requestClient;
	
	@ManyToOne
//	@Column(name="request_screen")	
	@JoinColumn(name="screen_id")
	private Screen requestScreen;
	
	public int getRequestId() {
		return requestId;
	}
	
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	
	public String getRequestTitle() {
		return requestTitle;
	}
	
	public void setRequestTitle(String requestTitle) {
		this.requestTitle = requestTitle;
	}
	
	public String getRequestClientDescription() {
		return requestClientDescription;
	}
	
	public void setRequestClientDescription(String requestClientDescription) {
		this.requestClientDescription = requestClientDescription;
	}
	
	public String getRequestDevDescription() {
		return requestDevDescription;
	}
	
	public void setRequestDevDescription(String requestDevDescription) {
		this.requestDevDescription = requestDevDescription;
	}

	public String getStatus() {
		return status;
	}	
	
	public LocalDateTime getRequestEntryDate() {
		return requestEntryDate;
	}

	public void setRequestEntryDate(LocalDateTime requestEntryDate) {
		this.requestEntryDate = requestEntryDate;
	}

	public LocalDateTime getRequestDevDate() {
		return requestDevDate;
	}

	public void setRequestDevDate(LocalDateTime requestDevDate) {
		this.requestDevDate = requestDevDate;
	}

	public LocalDateTime getRequestValidationDate() {
		return requestValidationDate;
	}

	public void setRequestValidationDate(LocalDateTime requestValidationDate) {
		this.requestValidationDate = requestValidationDate;
	}

	public LocalDateTime getRequestImplementationDate() {
		return requestImplementationDate;
	}

	public void setRequestImplementationDate(LocalDateTime requestImplementationDate) {
		this.requestImplementationDate = requestImplementationDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Client getRequestClient() {
		return requestClient;
	}
	public void setRequestClient(Client requestClient) {
		this.requestClient = requestClient;
	}
	
	public Screen getRequestScreen() {
		return requestScreen;
	}
	
	public void setRequestScreen(Screen requestScreen) {
		this.requestScreen = requestScreen;
	}	
}
