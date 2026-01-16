package com.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateDb {

	public static SessionFactory factory;
	
	public static SessionFactory getFactory() {
		 if (factory == null) {
	            factory = new Configuration()
	                    .configure()
	                    .buildSessionFactory();
	        }
	        return factory;
	}
}
