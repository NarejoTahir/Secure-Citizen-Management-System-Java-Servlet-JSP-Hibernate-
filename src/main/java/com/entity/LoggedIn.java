package com.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LoggedIn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private boolean isLoggedIn;
	
	private String sessionId;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private Users user;

	
	
	
	public LoggedIn(boolean isLoggedIn, Users user) {
		super();
		this.isLoggedIn = isLoggedIn;
		this.user = user;
		
	}
	
	

	public LoggedIn(boolean isLoggedIn, String sessionId, Users user) {
		super();
		this.isLoggedIn = isLoggedIn;
		this.sessionId = sessionId;
		this.user = user;
	}



	public LoggedIn() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	

	public String getSessionId() {
		return sessionId;
	}



	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}



	@Override
	public String toString() {
		return "LoggedIn [id=" + id + ", isLoggedIn=" + isLoggedIn + ", user=" + user + "]";
	}
	
	
	
	
	
	
	
}
