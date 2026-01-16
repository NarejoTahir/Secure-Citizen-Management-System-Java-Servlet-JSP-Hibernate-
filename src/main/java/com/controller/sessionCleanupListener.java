package com.controller;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.dao.loginStatusDao;
import com.dao.runtimeSessionRegister;
import com.db.HibernateDb;
import com.entity.Users;

@WebListener
public class sessionCleanupListener implements HttpSessionListener {

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        Users user = (Users) session.getAttribute("user");

        if (user != null) {
            loginStatusDao sd = new loginStatusDao(HibernateDb.getFactory());

            sd.logoutUser(user.getUserId());          // DB cleanup
            runtimeSessionRegister.removeSession(user.getUserId()); // static cleanup

            System.out.println("Auto logout cleanup for user: " + user.getName());
            
        }
    }
}