package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.entity.Countries;
import com.entity.Provinces;

public class addProvince {

	private Session sess;

	public addProvince(SessionFactory factory) {
		super();
		this.sess = factory.openSession();
	}
	
	public boolean addProvince(Provinces p) {
		boolean f=false;
		
		try {
			Transaction tx = sess.beginTransaction();
			
			sess.save(p);
			tx.commit();
			
			sess.close();
			
			f=true;
		}catch (Exception e) {
			System.out.println(e);
		}
		
		
		return f;
	}
	
	public List<Provinces> getProvince(){
		List<Provinces> pro=new ArrayList<>();
		
		try {
		sess.beginTransaction();
		pro = sess.createQuery("from Provinces", Provinces.class).list();
		
		sess.getTransaction().commit();
		}catch (Exception e) {
			System.out.println(e);
		}
		
				return pro;
	}
	
	
}
