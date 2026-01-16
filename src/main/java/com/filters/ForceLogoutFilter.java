package com.filters;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.UserContext;

import com.dao.loginStatusDao;
import com.dao.runtimeSessionRegister;
import com.db.HibernateDb;
import com.entity.LoggedIn;
import com.entity.Users;


@WebFilter(urlPatterns = {"/*"})
public class ForceLogoutFilter implements Filter {

    private loginStatusDao sd;

    @Override
    public void init(FilterConfig filterConfig) {
        sd = new loginStatusDao(HibernateDb.getFactory());
//        runtimeSessionRegister.clearAll();
        sd.logoutAllUsers();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        String uri = req.getRequestURI();
        String action=req.getParameter("action");

        
        if (session != null && session.getAttribute("user") != null) {
        	Users us=(Users) session.getAttribute("user");
            UserContext.setCurrentUser(Integer.toUnsignedLong(us.getUserId()));
            UserContext.setCurrentUserIp(req.getRemoteAddr());
        
            String sessionId = runtimeSessionRegister.getSession(us.getUserId());
            
            if(sessionId==null || !sessionId.equals(session.getId())) {
            	 	session.invalidate();
            	    resp.sendRedirect(req.getContextPath() + "/index.jsp?errorMsg=Session expired.");
            	    return;
            }
        }
        
        
        boolean isPublic = uri.endsWith("/index.jsp")
                || uri.contains("/register.jsp")
                || uri.contains("/css/")
                || uri.contains("/ajaxFunctions.js")
                || uri.contains("/images/")
                || ("login".equals(action))
                || ("forceLogin".equals(action))
        		|| ("logout".equals(action))
        		|| ("getDistricts".equals(action))
        		||	("addUser".equals(action));

   
        if (isPublic) {
            chain.doFilter(request, response);
            return; 
        }

        
       
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp?errorMsg=Kindly Login First");
            return;
        }

        
        
        Users user = (Users) session.getAttribute("user");
        LoggedIn loginRecord = sd.getLoginRecord(user.getUserId());

        
        if (loginRecord != null && !session.getId().equals(loginRecord.getSessionId())) {
            session.invalidate();
            resp.sendRedirect(req.getContextPath() + "/mainController?action=logout&errrorMsg=User Logout");
            return; 
        }

        chain.doFilter(request, response);
        
       
			UserContext.clear();
			
        }
		
       

    @Override
    public void destroy() {
    }
}



