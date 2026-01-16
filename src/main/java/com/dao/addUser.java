package com.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.db.hashPassword;
import com.entity.Districts;
import com.entity.Users;

public class addUser {

	private Session sess;

	public addUser(SessionFactory factory) {
		super();
		this.sess = factory.openSession();
	}
	
	public boolean addUser(Users u) {
		boolean f=false;
		try {
			Transaction tx = sess.beginTransaction();
			sess.save(u);
			
			tx.commit();
			f=true;
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return f;
	}
	
	 public List<Users> getUserByCnic(String cnic) {
	        List<Users> users = new ArrayList<>();
	        Transaction tx = sess.beginTransaction();
	        try {
	        	if(!tx.isActive()) {
	        		

	            String hql = "FROM Users u " +
	                         "JOIN FETCH u.district d " +
	                         "JOIN FETCH d.province p " +
	                         "JOIN FETCH p.country c " +
	                         "WHERE u.cnic = :cnicVal";

	            Query<Users> query = sess.createQuery(hql, Users.class);
	            query.setParameter("cnicVal", cnic);

	            users = query.getResultList();

	            sess.getTransaction().commit();
	        	}
	        	
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return users;
	    }

	 public List<Users> getUserssByCnic(String cnic) {
	        List<Users> users = new ArrayList<>();

	        try {
	            sess.beginTransaction();

	           
	            users = sess.createQuery("FROM Users u WHERE u.cnic = :cnicVal", Users.class)
	                    .setParameter("cnicVal", cnic)
	                    .getResultList();

	            
	            for (Users u : users) {
	                if (u.getDistrict() != null) {
	                    u.getDistrict().getName(); 
	                    if (u.getDistrict().getProvince() != null) {
	                        u.getDistrict().getProvince().getName();
	                        if (u.getDistrict().getProvince().getCountry() != null) {
	                            u.getDistrict().getProvince().getCountry().getCountry(); 
	                        }
	                    }
	                }
	            }

	            sess.getTransaction().commit();

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            sess.close();
	        }

	        return users;
	    }
	
	 
	 public List<Users> getAllUser(){
		 List<Users> u=new ArrayList<Users>();
		 
		 try {
			sess.beginTransaction();
			
			u = sess.createQuery(
			        "select distinct u from Users u " +
			        "join fetch u.district d " +
			        "join fetch d.province p " +
			        "join fetch p.country c",
			        Users.class
			).setCacheable(true).list();


			
			sess.getTransaction().commit();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		 
		 
		 return u;
	 }
	 
	 public Users getUserLogin(String cnic,String password) {
		 Users u=null;
		 
		 try {
			 	

			 	sess.beginTransaction();
			 	Query<Users> user = sess.createQuery("from Users where cnic=:cnic",Users.class);
			
			 	user.setParameter("cnic", cnic);
				
				u=user.uniqueResult();
				
				System.out.println(u);
				if(u!=null) {
					if(hashPassword.verifyPassword(password,u.getPassword())) {
							return u;
				}
				}
			 	sess.getTransaction().commit();
			 	sess.close();
			 	
		} catch (Exception e) {
			System.out.println(e);
		}
		 
		 
		 
		 return null;
	 }
	
	 public boolean addWithDifferentPassword(Users u) {
		 boolean f=false;
		 try {
			
			 Transaction tx = sess.beginTransaction();
			 Districts d = sess.get(Districts.class,u.getDistrict().getDistrictId());
			 
			
			 
			 String newPass = hashPassword.generatePassword(u.getPassword());
			 u.setPassword(newPass);

			
				 try {
					u.setDistrict(d);
					 sess.save(u);
					 tx.commit();
					 
					 sess.close();
					 f=true;
			
					 
				} catch (Exception e) {
					System.out.println(e);
				}
			
			 
			 
		} catch (Exception e) {
			System.out.println(e);
		}
		 
		 
		 return f;
	 }
	 
	 public Users getUserById(int id) {
		 Users u=null;
				 try {
					sess.beginTransaction();
					
					u = (Users) sess.get(Users.class, id);
					
				} catch (Exception e) {
					System.out.println(e);
				}
				 
				 return u;
	 }
	 
	 public Users UserByCnic(String cnic) {
	        Users users = null;

	        try {
	            sess.beginTransaction();

	           
	            Query<Users> q = sess.createQuery("FROM Users u WHERE u.cnic = :cnicVal", Users.class);
	                    q.setParameter("cnicVal", cnic);
	                    Users u = q.uniqueResult();

	            
	            
	                if (u.getDistrict() != null) {
	                    u.getDistrict().getName(); 
	                    if (u.getDistrict().getProvince() != null) {
	                        u.getDistrict().getProvince().getName();
	                        if (u.getDistrict().getProvince().getCountry() != null) {
	                            u.getDistrict().getProvince().getCountry().getCountry(); 
	                        }
	                    }
	                }
	            

	            sess.getTransaction().commit();

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            sess.close();
	        }

	        return users;
	    }
}

