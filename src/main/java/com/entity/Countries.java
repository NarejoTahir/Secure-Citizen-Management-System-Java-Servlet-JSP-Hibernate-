package com.entity;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Countries {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int countryId;
	private String country;
	@OneToMany(mappedBy = "country")
	private List<Provinces> province;
	
	
	public Countries(int countryId, String country, List<Provinces> province) {
		super();
		this.countryId = countryId;
		this.country = country;
		this.province = province;
	}

	

	public Countries() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getCountryId() {
		return countryId;
	}


	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public List<Provinces> getProvince() {
		return province;
	}


	public void setProvince(List<Provinces> province) {
		this.province = province;
	}



	@Override
	public String toString() {
		return "Countries [countryId=" + countryId + ", country=" + country + ", province=" + province + "]";
	}
	
	
	
	
	
	
	

	
}
