package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.entity.Districts;

public class addDistrict {

	
	private Session sess;

	public addDistrict(SessionFactory factory) {
		super();
		this.sess = factory.openSession();
	}
	
	public boolean addDistrict(Districts d) {
		boolean f=false;
		
		try {
			Transaction tx = sess.beginTransaction();
			
			sess.save(d);
			
			tx.commit();
			sess.close();
			
			f=true;
		} catch (Exception e) {
			System.out.println("something went wrong");
		}
		
		return f;
	}
	
	public List<Districts> getDistrict(){
		List<Districts> d=new ArrayList<>();
		
		try {
			sess.beginTransaction();
			d=sess.createQuery("from Districts",Districts.class).list();
			sess.getTransaction().commit();
		}catch (Exception e) {
			System.out.println(e);
		}
		
		
		return d;
	}
	
	public List<Districts> getDistrictsByProvinceId(int provinceId) {
	    List<Districts> districts = new ArrayList<>();

	    try  {
	        sess.beginTransaction();
	        districts = sess.createQuery("from Districts d where d.province.provinceId = :pid", Districts.class)
	            .setParameter("pid", provinceId).list();

	        sess.getTransaction().commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return districts;
	}
	
	
	
}
