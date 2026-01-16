package com.entity;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Districts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int districtId;
	private String name;
	@ManyToOne
	@JoinColumn(name="provinceId")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Provinces province;
	
	@OneToMany(mappedBy ="userId")
	private List<Users> user;

	
	public Districts() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Districts(int districtId, String name, Provinces province, List<Users> user) {
		super();
		this.districtId = districtId;
		this.name = name;
		this.province = province;
		this.user = user;
	}

	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Provinces getProvince() {
		return province;
	}

	public void setProvince(Provinces province) {
		this.province = province;
	}

	public List<Users> getUser() {
		return user;
	}

	public void setUser(List<Users> user) {
		this.user = user;
	}
	
	
	
	
}
