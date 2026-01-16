package com.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.internal.predicate.IsEmptyPredicate;

import com.db.HibernateDb;
import com.entity.LoggedIn;
import com.entity.Users;

public class loginStatusDao {

	 private SessionFactory factory;

	    public loginStatusDao(SessionFactory factory) {
	        this.factory = factory;
	    }

	    // Check if user is already logged in
	    public boolean isUserLoggedIn(int userId) {
	        try (Session sess = factory.openSession()) {
	            Transaction tx = sess.beginTransaction();

	            Query<LoggedIn> q = sess.createQuery(
	                "from LoggedIn l where l.user.userId = :id and l.isLoggedIn = true", LoggedIn.class
	            );
	            q.setParameter("id", userId);

	            LoggedIn loggedIn = q.uniqueResult();

	            tx.commit();
	            return loggedIn != null;
	        } catch (Exception e) {
	            System.out.println("Error checking login status: " + e);
	            return false;
	        }
	    }

	    // Log in user (returns false if already logged in)
	    public boolean loginUser(Users user, String sessionId) {
	        try (Session sess = factory.openSession()) {
	            Transaction tx = sess.beginTransaction();

	            Query<LoggedIn> q = sess.createQuery(
	                "from LoggedIn l where l.user.userId = :id", LoggedIn.class
	            );
	            q.setParameter("id", user.getUserId());

	            List<LoggedIn> list = q.getResultList();
	            LoggedIn loginRecord;

	            if (list.isEmpty()) {
	                loginRecord = new LoggedIn(true, sessionId, user);
	                sess.save(loginRecord);
	            } else {
	                loginRecord = list.get(0);
	                if (loginRecord.isLoggedIn()) {
	                    return false; // already logged in
	                }
	                loginRecord.setLoggedIn(true);
	                loginRecord.setSessionId(sessionId);
	                sess.update(loginRecord);
	            }

	            tx.commit();
	            return true;
	        } catch (Exception e) {
	            System.out.println("Error logging in user: " + e);
	            return false;
	        }
	    }

	    // Force login (invalidate previous session)
	    public boolean forceLogin(Users user, String newSessionId, HttpSession previousSession) {
	        try (Session sess = factory.openSession()) {
	            Transaction tx = sess.beginTransaction();

	            Query<LoggedIn> q = sess.createQuery(
	                "from LoggedIn l where l.user.userId = :id", LoggedIn.class
	            );
	            q.setParameter("id", user.getUserId());

	            LoggedIn loginRecord = q.uniqueResult();

	            if (loginRecord != null) {
	                // invalidate old session
	                if (previousSession != null) previousSession.invalidate();

	                loginRecord.setLoggedIn(true);
	                loginRecord.setSessionId(newSessionId);
	                sess.update(loginRecord);
	            } else {
	                loginRecord = new LoggedIn(true, newSessionId, user);
	                sess.save(loginRecord);
	            }

	            tx.commit();
	            return true;
	        } catch (Exception e) {
	            System.out.println("Error forcing login: " + e);
	            return false;
	        }
	    }
	    
	    



	    // Log out user
	    public boolean logoutUser(int userId) {
	        try (Session sess = factory.openSession()) {
	            Transaction tx = sess.beginTransaction();

	            Query<LoggedIn> q = sess.createQuery(
	                "from LoggedIn l where l.user.userId = :id and l.isLoggedIn = true", LoggedIn.class
	            );
	            q.setParameter("id", userId);

	            LoggedIn loginRecord = q.uniqueResult();

	            if (loginRecord != null) {
	                loginRecord.setLoggedIn(false);
	                loginRecord.setSessionId(null);
	                sess.update(loginRecord);
	            }

	            tx.commit();
	            return true;
	        } catch (Exception e) {
	            System.out.println("Error logging out user: " + e);
	            return false;
	        }
	    }
	
	    
	    public LoggedIn getLoginRecord(int userId) {
	        try (Session sess = factory.openSession()) {
	            Query<LoggedIn> q = sess.createQuery(
	                "from LoggedIn l where l.user.userId = :id", LoggedIn.class
	            );
	            q.setParameter("id", userId);
	            return q.uniqueResult();
	        }
	    }
	    
	    public void logoutAllUsers() {
	        try (Session sess = factory.openSession()) {
	            Transaction tx = sess.beginTransaction();

	           
	            int updated = sess.createQuery(
	                    "update LoggedIn l set l.isLoggedIn = false, l.sessionId = null"
	            ).executeUpdate();

	            tx.commit();
	            System.out.println("LogoutAllUsers: DB updated, " + updated + " users logged out.");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        System.out.println("LogoutAllUsers: static session map cleared.");
	    }
	    
//everything will be fine
}
