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
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

@Entity
@Audited
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String tehsilName;
	private String city;
	private String cnic;
	private String name;
	private String dob;
	private String password;
	
	
	@ManyToOne
	@JoinColumn(name="districtId")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Districts district;

	@NotAudited
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<LoggedIn> loginStatus;
	
	
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
	
	



	public Users(int userId, String tehsilName, String city, String cnic, String name, String dob, String password,
			Districts district) {
		super();
		this.userId = userId;
		this.tehsilName = tehsilName;
		this.city = city;
		this.cnic = cnic;
		this.name = name;
		this.dob = dob;
		this.password = password;
		this.district = district;
	}









	public String getDob() {
		return dob;
	}



	public void setDob(String dob) {
		this.dob = dob;
	}



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTehsilName() {
		return tehsilName;
	}

	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCnic() {
		return cnic;
	}

	public void setCnic(String cnic) {
		this.cnic = cnic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Districts getDistrict() {
		return district;
	}

	public void setDistrict(Districts district) {
		this.district = district;
	}

	


	public String getPassword() {
		return password;
	}









	public void setPassword(String password) {
		this.password = password;
	}









	@Override
	public String toString() {
		return "Users [userId=" + userId + ", tehsilName=" + tehsilName + ", city=" + city + ", cnic=" + cnic
				+ ", name=" + name + ", dob=" + dob + ", district=" + district + "]";
	}
	
	
	
	
	
	
}
