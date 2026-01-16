package com.db;

import org.mindrot.jbcrypt.BCrypt;

public class hashPassword {


    public static String generatePassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean verifyPassword(String password, String previousPass) {
        return BCrypt.checkpw(password, previousPass);
    }

}
