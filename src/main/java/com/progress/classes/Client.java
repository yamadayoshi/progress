package com.progress.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="client")
public class Client {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="client_id")
	private int clientId;	
	
	@NotNull	
	@Column(name="client_name", length=50)	
	private String clientName;
	
	@NotNull
	@Column(name="client_cnpj_cpf", length=20)
	private String clientCnpjCpf;
	
	@Column(name="client_email", length=50)
	private String clientEmail;
	
	@Column(name="client_phone", length=15)
	private String clientPhone;
	
	public Client() {
		
	}
	
	public Client(int id) {
		setClientId(id);
	}
	
	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientCnpjCpf() {
		return clientCnpjCpf;
	}
	public void setClientCnpjCpf(String clientCnpjCpf) {
		this.clientCnpjCpf = clientCnpjCpf;
	}
	public String getClientEmail() {
		return clientEmail;
	}
	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	public String getClientPhone() {
		return clientPhone;
	}
	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}	
}
