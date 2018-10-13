package com.revature.util;

import com.revature.repository.UserDao;


public class LoginUtil {

    //Method to check whether the first or last name in the account creation stage is valid
    public static boolean nameChecker(String name) {

        char ch;

        //Check if either last or first name meets the size req of 2-20 chars
        if(name.length() < 2 || name.length() > 20) {
            System.out.println("The name does not meet the size requirements");
            return false;
        }

        //Checks for any special characters
        for(int i = 0; i < name.length(); i++) {
            ch = name.charAt(i);
            if(Character.isLetter(ch) || Character.isWhitespace(ch)) {}
            else {
                System.out.println("The name cannot include any special characters");

                //Must return true to keep the outer loop in Login.java running
                return false;
            }
        }
        return true;
    }

    //Method to check if the username is valid for account creation
    public static boolean usernameChecker(String creds) {
        char ch;

        //Check if username meets the size req of 6-12 chars
        if(creds.length() < 6 || creds.length() > 12) {
            System.out.println("Your username must be between 8-12 characters");
            return false;
        }

        for(int i = 0; i < creds.length(); i++) {
            ch = creds.charAt(i);
            if(Character.isWhitespace(ch)) {
                System.out.println("Your choice cannot include spaces");
                return false;
            }
        }

        //Check if the username is unique
        boolean unique =  UserDao.checkUsernameUnique(creds);
        if(!unique) return false;

        return true;
    }

    //Checks password validity
    public static boolean passwordChecker(String creds) {
        char ch;

        //Check if pw meets the size req of 8-12 chars
        if(creds.length() < 8 || creds.length() > 12) {
            System.out.println("You choice must be between 8-12 characters");
            return false;
        }

        for(int i = 0; i < creds.length(); i++) {
            ch = creds.charAt(i);
            if(Character.isWhitespace(ch)) {
                System.out.println("Your choice cannot include spaces");
                return false;
            }
        }
        return true;
    }
}

