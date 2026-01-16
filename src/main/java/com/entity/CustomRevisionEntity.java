package com.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionListener;

@Entity
@Table
(name = "REVINFO")
@RevisionEntity(CustomRevisionListener.class)
public class CustomRevisionEntity extends DefaultRevisionEntity{

	
	private Long userId;
	
	private String ipAddress;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	
}
