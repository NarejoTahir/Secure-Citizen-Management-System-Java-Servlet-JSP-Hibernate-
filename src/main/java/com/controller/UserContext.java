package com.controller;



public class UserContext {


	    private static final ThreadLocal<Long> currentUser = new ThreadLocal<>();
	    private static final ThreadLocal<String> currentUserIp = new ThreadLocal<>();

	    public static void setCurrentUser(Long userId) {
	        currentUser.set(userId);
	    }

	    public static Long getCurrentUser() {
	        return currentUser.get();
	    }

	    public static void setCurrentUserIp(String ip) {
	        currentUserIp.set(ip);
	    }

	    public static String getCurrentUserIp() {
	        return currentUserIp.get();
	    }

	    public static void clear() {
	        currentUser.remove();
	        currentUserIp.remove();
	    }
}
