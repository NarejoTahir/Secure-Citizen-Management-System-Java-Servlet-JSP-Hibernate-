package com.entity;

import org.hibernate.envers.RevisionListener;

import com.controller.UserContext;

public class CustomRevisionListener implements RevisionListener{

	public void newRevision(Object revisionEntity) {
        CustomRevisionEntity rev = (CustomRevisionEntity) revisionEntity;
        rev.setUserId(UserContext.getCurrentUser());
        rev.setIpAddress(UserContext.getCurrentUserIp());
    }

}
