package com.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class runtimeSessionRegister {

	private static final Map<Integer, String> activeSession=new ConcurrentHashMap<Integer, String>();
	
	public static void addSession(int id, String sessionId) {
		activeSession.put(id, sessionId);
	}
	
	public static String getSession(int userId) {
		return activeSession.get(userId);
	}
	
	public static void removeSession(int userId) {
		activeSession.remove(userId);
	}
	
	public static void clearAll() {
		activeSession.clear();
	}
	
}
