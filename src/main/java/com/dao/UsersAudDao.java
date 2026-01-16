package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.query.Query;

import com.entity.Users;

public class UsersAudDao {

	private Session sess;

	public UsersAudDao(SessionFactory factory) {
		this.sess=factory.openSession();
	}
	
	
	public List<Object[]> getAddedUsers() {

	    AuditReader reader = AuditReaderFactory.get(sess);

	    try {
	        return reader.createQuery()
	            .forRevisionsOfEntity(Users.class, false, true)
	            .add(AuditEntity.revisionType().eq(RevisionType.ADD))
	            .getResultList();

	    } finally {
	        sess.close();
	    }
	
	}
}
