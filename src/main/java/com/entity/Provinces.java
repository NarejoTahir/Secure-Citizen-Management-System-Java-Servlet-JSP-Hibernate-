package com.entity;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
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
public class Provinces {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int provinceId;
	private String name;
	@ManyToOne()
	@JoinColumn(name="countryId")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Countries country;
	
	@OneToMany(mappedBy = "districtId",cascade = CascadeType.ALL)
	private List<Districts> district;

	

	public Provinces() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Countries getCountry() {
		return country;
	}

	public void setCountry(Countries country) {
		this.country = country;
	}

	public List<Districts> getDistrict() {
		return district;
	}

	public void setDistrict(List<Districts> district) {
		this.district = district;
	}

	
	
	
	
	
}
