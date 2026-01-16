package com.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.entity.Countries;

public class addCountry {

	private Session sess;

	public addCountry(SessionFactory factory) {
		super();
		this.sess = factory.openSession();
	}
	
	
	public boolean addCountry(Countries country) {
		boolean f=false;
		
		try {
			
		Transaction tx = sess.beginTransaction();
		
		sess.save(country);
		
		
		tx.commit();
			f=true;
		}catch (Exception e) {
			System.out.println(e);
		}
		return f;
		
	}
	
	public List<Countries> getCountries(){
		List<Countries> country=new ArrayList<>();
		
		try {
		sess.beginTransaction();
		country = sess.createQuery("from Countries", Countries.class).list();
		
		sess.getTransaction().commit();
		}catch (Exception e) {
			System.out.println(e);
		}
		
				return country;
	}
}
