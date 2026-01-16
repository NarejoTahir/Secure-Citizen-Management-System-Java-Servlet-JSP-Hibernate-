package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;

import com.db.HibernateDb;

import com.dao.*;
import com.entity.Countries;
import com.entity.Districts;
import com.entity.LoggedIn;
import com.entity.Provinces;
import com.entity.Users;
import com.sun.tools.sjavac.Log;

@WebServlet("/mainController")
public class MainController extends HttpServlet{

	
	private static final Logger logger=LogManager.getLogger(MainController.class);
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProcessRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProcessRequest(req, resp);
	}
	
	public void ProcessRequest(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		String action=req.getParameter("action");
		
		if(action==null || action.trim().isEmpty()) {
			logout(req,resp);
			
		}
//		else if(!"addCountry".equals(action) || !"addProvince".equals(action) || !"addDistrict".equals(action) || !"addUser".equals(action) || !"getDistrict".equals(action) || !"getUserByCnic".equals(action)){
//			logout(req,resp);
//		}
		HttpSession session = req.getSession(false);

	    if ("login".equals(action) || "forceLogin".equals(action)) {
	        switch (action) {
	            case "login":
	                login(req, resp);
	                break;
	            case "forceLogin":
	                forceLogin(req, resp);
	                break;
	        }
	    }
	    
	    if ("logout".equals(action)) {
	        logout(req, resp);
	        
	    }
	    
	    if (session != null && session.getAttribute("user") != null) {

	    	switch(action) {
			case "addCountry":
				addCountry(req, resp);
				break;
			case "addProvince":
				addProvince(req,resp);
				break;
			case "addDistrict":
				addDistrict(req, resp);
				break;
			case "addUser":
				addUser(req, resp);
				break;
				
			case "getDistricts":
				getDistrictsByProvince(req, resp);
				break;
			case "getUserByCnic":
				searchByUserCnic(req, resp);
				break;
//				default:
//					logout(req, resp);
//					break;
//					resp.sendRedirect(req.getContextPath()+"/index.jsp?errorMsg=Incorrect Requested Url");
			}
	    }	
//	    }else if(session == null && session.getAttribute("user") == null){
//	    	
//	    	session.setAttribute("errorMsg", "SomeThing Went Wrong");
//	    	resp.sendRedirect(req.getContextPath()+"/index.jsp");
//	    }
		
		
		
		
	}
	
	
	public void addCountry(HttpServletRequest req,HttpServletResponse resp) throws IOException {
	 
		String countryName=req.getParameter("countryName");
		
			Countries country=new Countries();
			country.setCountry(countryName);
	 
			System.out.println(countryName);
	 addCountry dao=new com.dao.addCountry(HibernateDb.getFactory());
	 
	 boolean f = dao.addCountry(country);
	 HttpSession session = req.getSession();
	 
	 if(f) {
		 session.setAttribute("succMsg", "Country Has Been Added SuccessFully");
		 resp.sendRedirect(req.getContextPath()+"/index.jsp");
	 }else {
		 session.setAttribute("errorMsg", "SomeThing Went Wrong");
		 resp.sendRedirect(req.getContextPath()+"/pages/addCountry.jsp");
	 }
	 
	 
	}
	
	public void addProvince(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String name=req.getParameter("provinceName");
		int id=Integer.parseInt(req.getParameter("countryId"));
		
		Countries c=new Countries();
		Provinces p=new Provinces();
		addProvince ap=new addProvince(HibernateDb.getFactory());
		
		c.setCountryId(id);
		
		p.setCountry(c);
		p.setName(name);
		
		HttpSession session = req.getSession();
		boolean f = ap.addProvince(p);
		
		if(f) {
			 session.setAttribute("succMsg", "Province Has Been Added SuccessFully");
			 resp.sendRedirect(req.getContextPath()+"/index.jsp");
		 }else {
			 session.setAttribute("errorMsg", "SomeThing Went Wrong");
			 resp.sendRedirect(req.getContextPath()+"/pages/addProvince.jsp");
		 }
	}
	
	public void addDistrict(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		int id=Integer.parseInt(req.getParameter("provinceId"));
		String districtName=req.getParameter("districtName");
		
		Provinces p=new Provinces();
		p.setProvinceId(id);
		
		Districts d=new Districts();
		
		d.setName(districtName);
		d.setProvince(p);
		
		addDistrict dao=new addDistrict(HibernateDb.getFactory());
		
		boolean f = dao.addDistrict(d);
		
		HttpSession session = req.getSession();
		
		if(f) {
			 session.setAttribute("succMsg", "District Has Been Added SuccessFully");
			 resp.sendRedirect(req.getContextPath()+"/index.jsp");
		 }else {
			 session.setAttribute("errorMsg", "SomeThing Went Wrong");
			 resp.sendRedirect(req.getContextPath()+"/pages/addDistriict.jsp");
		 }
		
		
	}
	
	public void addUser(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		addUser au=new addUser(HibernateDb.getFactory());
		Districts d=new Districts();
		Users u=new Users();
		
		String userName=req.getParameter("fullName");
		String dob=req.getParameter("dob");
		int districtId=Integer.parseInt(req.getParameter("districtId"));
		String cnic=req.getParameter("cnic");
		String tehsil=req.getParameter("tehsil");
		String city=req.getParameter("city");
		String password=req.getParameter("pass");
		
		d.setDistrictId(districtId);
		
		u.setName(userName);
		u.setCnic(cnic);
		u.setDistrict(d);
		u.setCity(city);
		u.setTehsilName(tehsil);
		u.setDob(dob);
		u.setPassword(password);
		
		
		boolean f = au.addWithDifferentPassword(u);
		
		
		HttpSession session = req.getSession();
		
		if(f) {
			if(session.getAttribute("user")!=null) {
				resp.sendRedirect(req.getContextPath()+"/pages/home.jsp");	
			}else {
				 session.setAttribute("succMsg", "User Has Been Added SuccessFully");
				 resp.sendRedirect(req.getContextPath()+"/index.jsp");
			}
		}else {
			 session.setAttribute("errorMsg", "SomeThing Went Wrong");
			 resp.sendRedirect(req.getContextPath()+"/pages/addUser.jsp");
		 }
		
		
		
	}
	
	public void getDistrictsByProvince(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		
		int provinceId = Integer.parseInt(req.getParameter("provinceId"));
		
		addDistrict dao=new addDistrict(HibernateDb.getFactory());
		
		List<Districts> districts = dao.getDistrictsByProvinceId(provinceId);
		
		resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        

        StringBuilder json = new StringBuilder();
        json.append("[");
        for (int i = 0; i < districts.size(); i++) {
            Districts d = districts.get(i);
            json.append("{\"id\":").append(d.getDistrictId())
                .append(",\"name\":\"").append(d.getName()).append("\"}");
            if (i < districts.size() - 1) json.append(",");
        }
        json.append("]");
        out.print(json.toString());
        out.flush();
        
	}
	
	
	public void searchByUserCnic(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		

		 	String cnic = req.getParameter("cnic");

		    addUser dao = new addUser(HibernateDb.getFactory());
		    List<Users> users = dao.getUserssByCnic(cnic);
		    System.out.println(cnic);
		    System.out.println("user"+ users);

		    resp.setContentType("application/json");
		    PrintWriter out = resp.getWriter();

		    StringBuilder json = new StringBuilder();

		    if (users.isEmpty()) {
		        json.append("[{\"message\":\"No data found\"}]");
		    } else {
		        json.append("[");
		        for (int i = 0; i < users.size(); i++) {
		            Users u = users.get(i);
		            json.append("{")
		                .append("\"id\":").append(u.getUserId()).append(",")
		                .append("\"name\":\"").append(u.getName()).append("\",")
		                .append("\"dob\":\"").append(u.getDob()).append("\",")
		                .append("\"country\":\"").append(u.getDistrict().getProvince().getCountry().getCountry()).append("\",")
		                .append("\"province\":\"").append(u.getDistrict().getProvince().getName()).append("\",")
		                .append("\"district\":\"").append(u.getDistrict().getName()).append("\",")
		                .append("\"tehsil\":\"").append(u.getTehsilName()).append("\",")
		                .append("\"cnic\":\"").append(u.getCnic()).append("\"")
		                .append("}");
		            if (i < users.size() - 1) json.append(",");
		        }
		        json.append("]");
		    }

		    out.print(json.toString());
		    out.flush();
		    
	}
	
	public void login(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		
		
		addUser au=new addUser(HibernateDb.getFactory());

		String cnic=req.getParameter("cnic");
		String pass=req.getParameter("password");

		
		String addr = req.getRemoteAddr();
		System.out.println(cnic +" and from login " +pass);
		
		
		Users user = au.getUserLogin(cnic, pass);
		
		HttpSession session = req.getSession();

////	
		

		    if (user == null) {
		        // User not found
		    	logger.warn("Invalid Crendentials" +cnic +"Password"+pass);
		        session.setAttribute("errorMsg", "Invalid CNIC/Password");
		        resp.sendRedirect(req.getContextPath() + "/index.jsp");
		        return;
		    }

		    	loginStatusDao sd = new loginStatusDao(HibernateDb.getFactory());

		    	String activeSessionId=runtimeSessionRegister.getSession(user.getUserId());
		    	
		    	if (activeSessionId != null && !activeSessionId.equals(session.getId())) {
		    	
		    	  resp.setContentType("text/html;charset=UTF-8");

		    	    PrintWriter out = resp.getWriter();

		    	    out.println("<!DOCTYPE html>");
		    	    out.println("<html>");
		    	    out.println("<head>");
		    	    out.println("<title>Nadra System</title>");
		    	    out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
		    	    out.println("</head>");
		    	    out.println("<body>");

		    	    out.println("<h3>You are already logged in on another device</h3>");

		    	    out.println("<form method='post' action='" + req.getContextPath() + "/mainController?action=forceLogin'>");

		    	    out.println("<input type='hidden' name='pass' value='"+pass+"'>");
		    	    out.println("<input type='hidden' name='cnic' value='" + cnic + "'>");


		    	    out.println("<button type='submit' class='btn btn-danger'>");
		    	    out.println("Logout other session & Login");
		    	    out.println("</button>");

		    	    out.println("</form>");

		    	    out.println("</body>");
		    	    out.println("</html>");

		    	    return;
		    	}
		    
//		    	System.out.println("User upon success "+user);
		    if (user != null) {
		        boolean success = sd.loginUser(user, session.getId());
//		        	System.out.println(success);
		        if (success) {
		        	runtimeSessionRegister.addSession(user.getUserId(), session.getId());
//		        	System.out.println("Session Id: "+runtimeSessionRegister.getSession(user.getUserId()));
		        	logger.info("User Login SuccessFully "+ user.getName()+" IP Address Is :"+ addr);
		            session.setAttribute("user", user);
//		            session.setMaxInactiveInterval(5*60);
		            resp.sendRedirect(req.getContextPath() + "/pages/home.jsp");
		        } else {
		        	boolean loggedOut = sd.logoutUser(user.getUserId());
		 	        runtimeSessionRegister.removeSession(user.getUserId());
		 	        session.invalidate();
		            resp.sendRedirect(req.getContextPath()+"/index.jsp?errorMsg=Session Expired Login Again");
		        }
		    }
		}

		
			


	
	
	public void forceLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	    String cnic = req.getParameter("cnic");
	    String password = req.getParameter("pass");

//	    System.out.println(cnic +" and from Force " +password);
	    
	    addUser au = new addUser(HibernateDb.getFactory());
	    Users user = au.getUserLogin(cnic, password);
	    HttpSession newSession = req.getSession();
	    
	    System.out.println("User Is returning" +user);

	    if (user == null) {
//	    	System.out.println("User Returning"+user);
	        newSession.setAttribute("errorMsg", "Invalid CNIC/PASSWORD");
	        resp.sendRedirect(req.getContextPath() + "/index.jsp");
	        return;
	    }

	    loginStatusDao sd = new loginStatusDao(HibernateDb.getFactory());

	    
	    HttpSession oldSession = null; 
	    boolean forced = sd.forceLogin(user, newSession.getId(), oldSession);

	    if (forced) {
	    	runtimeSessionRegister.addSession(user.getUserId(), newSession.getId());
	    	logger.info("Login From Another Browser" +user.getName());
	        newSession.setAttribute("user", user);
	        resp.sendRedirect(req.getContextPath() + "/pages/home.jsp");
	    } else {
	        newSession.setAttribute("errorMsg", "Could not force login");
	        resp.sendRedirect(req.getContextPath() + "/index.jsp");
	    }
	}
	
	
	
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	    HttpSession session = req.getSession(false);
	    String msg=req.getParameter("errorMsg");

	    System.out.println(msg);
	    if (session != null && session.getAttribute("user") != null) {
	        Users user = (Users) session.getAttribute("user");
	        System.out.println("User detail: "+user);
	        loginStatusDao sd = new loginStatusDao(HibernateDb.getFactory());

	        boolean loggedOut = sd.logoutUser(user.getUserId());
	        runtimeSessionRegister.removeSession(user.getUserId());
	       
	        session.invalidate();

	        if (loggedOut) {
	        	if(msg==null) {
	        		msg="Logout SuccessFull";
	        	}
	        	logger.info("Logout SuccessFull"+user.getName());
	            resp.sendRedirect(req.getContextPath() + "/index.jsp?errorMsg=User Logout");
	        } else {
	            resp.sendRedirect(req.getContextPath() + "/index.jsp?errorMsg=Something went wrong");
	        }
	    } else {
	        resp.sendRedirect(req.getContextPath() + "/index.jsp");
	    }
	}
	
	
	
	
	}

	
	

